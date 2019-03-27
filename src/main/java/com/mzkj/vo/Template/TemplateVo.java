package com.mzkj.vo.Template;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Description: 保存修改vo
 * @Author: zw
 * @Date: 2019/3/26 17:21
 * @Version: 1.0
 */
public class TemplateVo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String TEMPLATE_ID;

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
