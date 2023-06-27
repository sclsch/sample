/**
 * All rights Reserved, Designed By tuohao.
 *
 * @author: suncl
 * @date: 2019/7/8 10:09
 */
package work.hdjava.sample.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * code枚举
 * @Author: suncl
 * @Date: 2021/7/9 18:41
 */
public enum CodeEnum {
    QUERY_COUNT_UPPER_LIMIT("1000", "请求达到上限"),
    SESSION_OVERTIME("1001","session超时"),
    REQ_INVALID("1002","请求格式有误"),
    NO_AUTH("1003","无权访问,请先进行实名认证"),
    LOGIN_FAIL("1004","账号密码不对"),
    LOGIN_BANNED("1005","用户处于封禁状态无法登录"),
    NO_REGISTER_OR_NO_LOGIN("1006","未注册或者未登录"),
    NO_REGISTER("1007","未注册"),
    /**
     * 业务参数
     */
    HOUSE_LOCK("9000","已查封"),
    HOUSE_MORT("9001","已抵押"),
    HOUSE_NO("9002","查无此房"),
    HOUSE_ING("9003","该房子正在交易不允许再次发起业务"),
    HOUSE_CHECK_WAIT("9004","等待房屋核验"),
    HOUSE_CHECK_YES("9005","房屋核验通过"),
    HOUSE_CHECK_REJECT("9006","房屋核验驳回"),
    HOUSE_CHECK_NO("9007","房屋核验未通过")

    ,JWT_TOKEN_EXPIRE("603","token校验异常")
    ,BAD_GATEWAY("502","网关服务异常")
    ,AUTHORIZATION_HEADER_IS_EMPTY("600","请求头中的token为空")
    ,GET_TOKEN_KEY_ERROR("601","远程获取TokenKey异常")
    , GEN_PUBLIC_KEY_ERROR("602","生成公钥异常")

    /**
     *   return handleException.writeError(exchange,
     *                         "Token was not recognised, token: ".concat(access_token));
     *             }
     *             if (StringUtils.isBlank(entity.getBody())) {
     *                 return handleException.writeError(exchange,
     *                         "This token is invalid: ".concat(access_token));
     *             }
     *         } catch (Exception e) {
     *             return handleException.writeError(exchange,
     *                     "Token was not recognised, token: ".concat(access_token));
     */
    , TOKEN_NOT_RECOGNISED("604","token无法识别")
    , TOKEN_INVALID("605","token 失效")

    ;



    private static Map<String , CodeEnum> nameMap = new HashMap<String, CodeEnum>(
            20);
    private static Map<String, CodeEnum> codeMap = new HashMap<String, CodeEnum>(
            20);

    static {
        CodeEnum[] allValues = CodeEnum.values();
        for (CodeEnum obj : allValues) {
            nameMap.put(obj.getName(), obj);
            codeMap.put(obj.getCode(), obj);
        }
    }

    private String code;
    private String name;

    CodeEnum(String code, String name) {
        this.name = name;
        this.code = code;

    }

    public static CodeEnum parseByName(String name) {
        return nameMap.get(name);
    }

    public static CodeEnum parseByCode(String code) {
        return codeMap.get(code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
