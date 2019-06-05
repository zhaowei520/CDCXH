package com.mzkj.service.oainterface;

import com.mzkj.bean.SocialSecurityBean;

/**
 * @Author: luosc
 * @Description:
 * @Date:created in 9:50 2019-05-29
 */
public interface ProcessInterface {

    /**
     * 社保工单启动流程
     * return
     * Author luosc
     * param
     * Date 2019-05-29 9:52
     */
    public String processStart(SocialSecurityBean socialSecurityBean) throws Exception;
}
