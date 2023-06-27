package work.hdjava.sample.common.dto;

/**
 * All rights Reserved, Designed By 八方浩达.
 *
 * @author: suncl
 * @date: 2022/12/15 13:43
 * @Copyright ?2022 八方浩达. All rights reserved.
 * 注意：本内容仅限于八方浩达内部传阅，禁止外泄以及用于其他的商业用途。
 */
import lombok.Data;

import java.util.Map;

@Data
public class TokenInfo {

    private String access_token;

    private String token_type;

    private String refresh_token;

    private String scope;

    private Map<String,Object> additionalInfo;

}
