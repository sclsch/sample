/**
 * All rights Reserved, Designed By tuohao.
 *
 * @author: suncl
 * @date: 2019/7/23 11:27
 */
package work.hdjava.sample.common.entity;

import work.hdjava.sample.common.enums.CodeEnum;
import work.hdjava.sample.common.enums.ResEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 统一返回实体
 *
 * @Author: suncl
 * @Date: 2021/7/9 18:41
 */
@ApiModel("响应实体封装类")
public class ResEntity<T> implements Serializable {
    /**
     * 返回code 1 成功   0 是失败
     */
    @ApiModelProperty("响应码")
    private Integer code;

    /**
     * 返回消息
     */
    @ApiModelProperty("响应信息")
    private String message;

    /**
     * 消息实体
     */
    @ApiModelProperty("响应实体")
    private T data;

    /**
     * 自定义错误编码
     *
     * @return
     */
    @ApiModelProperty("自定义响应错误编码")
    private String detailCode;

    @ApiModelProperty("自定义响应错误信息")
    private String detailMsg;


    public ResEntity fail(String detailMsg) {
        this.code = ResEnum.FAIL.getCode();
        this.message = ResEnum.FAIL.getDescription();
        this.detailMsg = detailMsg;
        return this;
    }

    public ResEntity error(String detailMsg) {
        return this.fail(detailMsg);
    }

    public ResEntity fail(T data) {
        this.code = ResEnum.FAIL.getCode();
        this.message = ResEnum.FAIL.getDescription();
        this.data = data;
        return this;
    }

    public ResEntity fail() {
        this.code = ResEnum.FAIL.getCode();
        this.message = ResEnum.FAIL.getDescription();
        return this;
    }

    public ResEntity success(String detailMsg) {
        this.code = ResEnum.SUCCESS.getCode();
        this.message = ResEnum.SUCCESS.getDescription();
        this.detailMsg = detailMsg;
        return this;
    }

    public ResEntity success() {
        this.code = ResEnum.SUCCESS.getCode();
        this.message = ResEnum.SUCCESS.getDescription();
        return this;
    }

    public ResEntity success(T data) {
        this.code = ResEnum.SUCCESS.getCode();
        this.message = ResEnum.SUCCESS.getDescription();
        this.data = data;
        return this;
    }


    public ResEntity rows(int rows) {
        if (rows > 0) {
            this.code = ResEnum.SUCCESS.getCode();
            this.message = ResEnum.SUCCESS.getDescription();
        } else {
            this.code = ResEnum.FAIL.getCode();
            this.message = ResEnum.FAIL.getDescription();
        }
        return this;
    }

    /**
     * @param resEnum 是否成功枚举
     */
    public ResEntity(ResEnum resEnum) {
        this.code = resEnum.getCode();
        this.message = resEnum.getDescription();
    }


    /**
     * @param resEnum 是否成功枚举
     * @param data    返回对象
     */
    public ResEntity(ResEnum resEnum, T data) {
        this.code = resEnum.getCode();
        this.message = resEnum.getDescription();
        this.data = data;
    }

    /**
     * @param resEnum  是否成功枚举
     * @param data     返回对象
     * @param codeEnum 错误枚举
     */
    public ResEntity(ResEnum resEnum, T data, CodeEnum codeEnum) {
        this.code = resEnum.getCode();
        this.message = resEnum.getDescription();
        this.data = data;
        this.detailCode = codeEnum.getCode();
        this.detailMsg = codeEnum.getName();
    }

    /**
     * @param resEnum   是否成功枚举
     * @param data      返回对象
     * @param codeEnum  错误枚举
     * @param detailMsg 自定义错误信息
     */
    public ResEntity(ResEnum resEnum, T data, CodeEnum codeEnum, String detailMsg) {
        this.code = resEnum.getCode();
        this.message = resEnum.getDescription();
        this.data = data;
        this.detailCode = codeEnum.getCode();
        this.detailMsg = detailMsg;
    }

    /**
     * @param resEnum    是否成功枚举
     * @param data       返回对象
     * @param detailCode 详细错误编码
     * @param detailMsg  自定义错误信息
     */
    public ResEntity(ResEnum resEnum, T data, String detailCode, String detailMsg) {
        this.code = resEnum.getCode();
        this.message = resEnum.getDescription();
        this.data = data;
        this.detailCode = detailCode;
        this.detailMsg = detailMsg;
    }

    /**
     * @param resEnum    是否成功枚举
     * @param detailCode 详细错误编码
     * @param detailMsg  自定义错误信息
     */
    public ResEntity(ResEnum resEnum, String detailCode, String detailMsg) {
        this.code = resEnum.getCode();
        this.message = resEnum.getDescription();
        this.detailCode = detailCode;
        this.detailMsg = detailMsg;
    }

    public ResEntity(ResEnum resEnum, String detailMsg) {
        this.code = resEnum.getCode();
        this.message = resEnum.getDescription();
        this.detailMsg = detailMsg;
    }

    public ResEntity(ResEnum resEnum, CodeEnum codeEnum) {
        this.code = resEnum.getCode();
        this.message = resEnum.getDescription();
        this.detailCode = codeEnum.getCode();
        this.detailMsg = codeEnum.getName();
    }

    public ResEntity() {
    }

    /**
     * detailcode为空时调用
     *
     * @param resEnum   是否成功枚举
     * @param data      返回对象
     * @param detailMsg 自定义错误信息
     */
    public ResEntity(ResEnum resEnum, T data, String detailMsg) {
        this.code = resEnum.getCode();
        this.message = resEnum.getDescription();
        this.data = data;
        this.detailMsg = detailMsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }


}
