package work.hdjava.sample.gateway.filter;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.PathMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import work.hdjava.sample.common.enums.CodeEnum;
import work.hdjava.sample.common.exception.GateWayException;
import work.hdjava.sample.gateway.properties.NotAuthUrlProperties;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Order(0)
@EnableConfigurationProperties(value = NotAuthUrlProperties.class)
@Slf4j
public class AuthenticationFilter implements GlobalFilter, InitializingBean {
    /**
     * 认证服务器许可我们的网关的clientId(需要在oauth_client_details表中配置)
     */
    private static final String CLIENT_ID = "app";

    /**
     * 认证服务器许可我们的网关的client_secret(需要在oauth_client_details表中配置)
     */
    private static final String CLIENT_SECRET = "app";
    private static final String AUTH_HEADER = "Bearer ";
    /**
     * 请求各个微服务 不需要用户认证的URL
     */
    @Autowired
    private NotAuthUrlProperties notAuthUrlProperties;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        
        String currentUrl = exchange.getRequest().getURI().getPath();
        log.info("currentUrl:",currentUrl);
        //过滤不需要认证的url
        if(shouldSkip(currentUrl)) {
            log.info("跳过认证的URL:{}",currentUrl);
            return chain.filter(exchange);
        }
        log.info("需要认证的URL:{}",currentUrl);
        //2. 获取token
        // 从请求头中解析 Authorization  value:  bearer xxxxxxx
        // 或者从请求参数中解析 access_token
        //第一步:解析出我们Authorization的请求头  value为: “bearer XXXXXXXXXXXXXX”
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        //第二步:判断Authorization的请求头是否为空
        if(StringUtils.isEmpty(authHeader)) {
            log.warn("需要认证的url,请求头为空");
            throw new GateWayException(CodeEnum.AUTHORIZATION_HEADER_IS_EMPTY);
        }

        //3. 校验token

        //第一步:封装请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(CLIENT_ID,CLIENT_SECRET);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);

        String access_token = authHeader.contains(AUTH_HEADER) ? StringUtils.substringAfter(authHeader, AUTH_HEADER) : authHeader;
        String checkTokenUrl = "http://auth-server/oauth2/check_token?token=".concat(access_token);
        try {
            // 发送远程请求，验证 token
            ResponseEntity<String> exchangeCheckToken = restTemplate.exchange(checkTokenUrl, HttpMethod.GET, entity, String.class);
         //   ResponseEntity<String> entity = restTemplate.getForEntity(checkTokenUrl, String.class);
            // token 无效的业务逻辑处理
            if (HttpStatus.OK != exchangeCheckToken.getStatusCode()) {
                throw new GateWayException(CodeEnum.TOKEN_NOT_RECOGNISED);

            }
            if (StringUtils.isBlank(exchangeCheckToken.getBody())) {
                throw new GateWayException(CodeEnum.TOKEN_INVALID);
            }
            log.info("tokenInfo:{}",exchangeCheckToken.getBody());
            /**
             * {"aud":["app"],"user_name":"ziya","scope":["web"],"active":true,"exp":1679409165,"authorities":["$2a$10$MmktEVRp7e5OS/IgkBtdbu71MXQRzXYxXw.aiDiCm9jd7dJZuZTZG"],"client_id":"app"}
             */
//            CheckTokenDto checkTokenDto = JSON.parseObject(exchangeCheckToken.getBody(), CheckTokenDto.class);
//            String perms = checkTokenDto.getAuthorities().stream().map(String::valueOf).collect(Collectors.joining(","));
            ServerHttpRequest request = exchange.getRequest().mutate()
                  //  .header("userName",checkTokenDto.getUser_name())
                 //   .header("permissions", perms)
                    .build();

            //将现在的request 变成 change对象
            exchange.mutate().request(request).build();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new GateWayException(CodeEnum.TOKEN_NOT_RECOGNISED);
        }


        return chain.filter(exchange);
    }


    /**
     * jwt的公钥,需要网关启动,远程调用认证中心去获取公钥
     */
    private PublicKey publicKey;

    @Autowired
    private RestTemplate restTemplate;

    //afterPropertiesSet     bean初始化期间调的
    @Override
    public void afterPropertiesSet() throws Exception {
        //获取公钥  TODO
//        //restTemplate  http://tulingmall-authcenter/oauth/token_key
//        this.publicKey = JwtUtils.genPulicKey(restTemplate);
    }

    
    /**
     * 方法实现说明:不需要授权的路径
     * @author:smlz
     * @param currentUrl 当前请求路径
     * @return:
     * @exception:
     * @date:2019/12/26 13:49
     */
    private boolean shouldSkip(String currentUrl) {
        //路径匹配器(简介SpringMvc拦截器的匹配器)
        //比如/oauth/** 可以匹配/oauth/token    /oauth/check_token等
        PathMatcher pathMatcher = new AntPathMatcher();
        for(String skipPath:notAuthUrlProperties.getShouldSkipUrls()) {
            if(pathMatcher.match(skipPath,currentUrl)) {
                return true;
            }
        }
        return false;
    }

    private ServerWebExchange wrapHeader(ServerWebExchange serverWebExchange,Claims claims) {

//        String loginUserInfo = JSON.toJSONString(claims);
//
//        //log.info("jwt的用户信息:{}",loginUserInfo);
//
//        String memberId = claims.get("additionalInfo", Map.class).get("memberId").toString();
//
//        String nickName = claims.get("additionalInfo",Map.class).get("nickName").toString();
//        List<String> permissions = (List<String>) claims.get("additionalInfo",Map.class).get("permissions");
//        //向headers中放文件，记得build
//        String perms = permissions.stream().map(String::valueOf).collect(Collectors.joining(","));
        ServerHttpRequest request = serverWebExchange.getRequest().mutate()
//                .header("userName",claims.get("user_name",String.class))
//                .header("memberId",memberId)
//                .header("nickName",nickName)
//                .header("permissions", perms)
                .build();

        //将现在的request 变成 change对象
        return serverWebExchange.mutate().request(request).build();
    }
}

