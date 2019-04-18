package com.mzkj.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 基础vo
 * @Author: zw
 * @Date: 2019/3/27 16:22
 * @Version: 1.0
 */
public class BaseVo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value="第几页",name="pageNum",example="1")
    private Integer pageNum = 1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value="每页数量",name="pageSize",example="10")
    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
