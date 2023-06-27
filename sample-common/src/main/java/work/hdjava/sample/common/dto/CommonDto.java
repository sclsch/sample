package work.hdjava.sample.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By 八方浩达.
 *  通用dto，需要其他dto继承
 * @author: suncl
 * @date: 2023/1/3 11:42
 * @Copyright ?2022 八方浩达. All rights reserved.
 * 注意：本内容仅限于八方浩达内部传阅，禁止外泄以及用于其他的商业用途。
 */
@Data
@ApiModel("通用dto")
public class CommonDto {

    /**
     * 页大小
     */
    @ApiModelProperty("页大小")
    private  Integer limit = 10;
    /**
     * 当前页码
     */
    @ApiModelProperty("当前页码")
    private  Integer page = 1;
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private String startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private String endTime;

    /** 请求参数 */
    @ApiModelProperty("map参数")
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
