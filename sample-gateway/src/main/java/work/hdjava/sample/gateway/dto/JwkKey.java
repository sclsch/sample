package work.hdjava.sample.gateway.dto;

import lombok.Data;

/**
 * All rights Reserved, Designed By hdjava.work.
 *
 * @author: suncl
 * @date: 2023/7/19 22:05
 * @Copyright ?2022 hdjava.work. All rights reserved.
 * 注意：本内容仅限于hdjava.work内部传阅，禁止外泄以及用于其他的商业用途。
 */
@Data
public class JwkKey {
    private String kty;
    private String e;
    private String kid;
    private String n;
}
