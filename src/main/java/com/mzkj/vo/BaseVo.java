package com.mzkj.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 作用
 * @Author: zw
 * @Date: 2019/3/27 16:22
 * @Version: 1.0
 */
public class BaseVo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value="第几页",name="pageIndex",example="1")
    private Integer pageIndex = 1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value="每页数量",name="pageSise",example="10")
    private Integer pageSise = 10;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSise() {
        return pageSise;
    }

    public void setPageSise(Integer pageSise) {
        this.pageSise = pageSise;
    }

}
