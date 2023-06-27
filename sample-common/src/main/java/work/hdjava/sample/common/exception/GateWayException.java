package work.hdjava.sample.common.exception;

import work.hdjava.sample.common.enums.CodeEnum;
import lombok.Data;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:网关异常类
* @author: suncl
* @createDate: 2020/1/22 10:52
* @version: 1.0
*/
@Data
public class GateWayException extends RuntimeException{

    private String code;

    private String message;

    public GateWayException(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getName();
    }
}
