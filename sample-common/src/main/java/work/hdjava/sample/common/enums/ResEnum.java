package work.hdjava.sample.common.enums;

import java.io.Serializable;

/**
 * @author: suncl
 * @date: 2019/8/1 15:07
 * @version: V1.0
 */
public enum ResEnum implements Serializable {
    SUCCESS(0,"成功"),
    FAIL(1,"失败");

    public Integer getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    private Integer code;
    private String description;
    ResEnum(Integer code, String description){
        this.code = code;
        this.description = description;
    }
}
