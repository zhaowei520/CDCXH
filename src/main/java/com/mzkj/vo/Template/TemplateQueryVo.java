package com.mzkj.vo.Template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import com.mzkj.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 查询vo
 * @Author: zw
 * @Date: 2019/3/26 17:21
 * @Version: 1.0
 */
@ApiModel(value="Template查询对象",description="Template查询")
public class TemplateQueryVo extends BaseVo {
    @ApiModelProperty(value="模板id",name="TEMPLATE_ID",example="1")
    String TEMPLATE_ID;
    @ApiModelProperty(value="用户id",name="USER_ID",example="1")
    String USER_ID;


    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }


}
