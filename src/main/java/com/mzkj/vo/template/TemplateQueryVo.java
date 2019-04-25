package com.mzkj.vo.template;

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
    @ApiModelProperty(value="日期",name="TEMPLATE_DATE",example="2019-05-24")
    String TEMPLATE_DATE;
    @ApiModelProperty(value="模糊查询条件",name="queryCodition",example="")
    String queryCodition;

    public String getQueryCodition() {
        return queryCodition;
    }

    public void setQueryCodition(String queryCodition) {
        this.queryCodition = queryCodition;
    }

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

    public String getTEMPLATE_DATE() {
        return TEMPLATE_DATE;
    }

    public void setTEMPLATE_DATE(String TEMPLATE_DATE) {
        this.TEMPLATE_DATE = TEMPLATE_DATE;
    }
}
