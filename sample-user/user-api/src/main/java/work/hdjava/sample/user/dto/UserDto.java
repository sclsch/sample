package work.hdjava.sample.user.dto;

import lombok.Data;

import java.util.Date;

/**
 * All rights Reserved, Designed By 八方浩达.
 * @author: suncl
 * @date: 2023/6/9 21:13
 * @Copyright ?2022 八方浩达. All rights reserved.
 * 注意：本内容仅限于八方浩达内部传阅，禁止外泄以及用于其他的商业用途。
 */

/**
    * 用户表
    */

@Data
public class UserDto {
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;
}