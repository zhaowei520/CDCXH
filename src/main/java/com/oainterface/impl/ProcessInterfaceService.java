package com.oainterface.impl;

import com.alibaba.fastjson.JSON;
import com.fh.controller.springboot.trunkservice.OaInterfaceForSpringBoot;
import com.mzkj.bean.SocialSecurityBean;
import com.mzkj.facade.vo.Result;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PropertiesUtil;
import com.oainterface.ProcessInterface;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @Author: luosc
 * @Description:
 * @Date:created in 9:50 2019-05-29
 */
@Service("processInterfaceService")
public class ProcessInterfaceService implements ProcessInterface {
    @Resource
    private OaInterfaceForSpringBoot oaInterfaceForSpringBootService;
    @Override
    public String processStart(SocialSecurityBean socialSecurityBean) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("SERVERNAME", "socialSecurityService");
        //map.put("CONTRACTNAME", "indcomconService");    //合同service
        map.put("USERNAME", Jurisdiction.getUsername()); // 指派代理人为当前用户
        map.put("socialSecurityId", socialSecurityBean.getSocialSecurityId());
        //放置serviceName
        map.put("serviceName", "socialSecurityService");
        //map.put("CUSTOMER_TEL",pd.get("CUSTOMER_TEL"));
        map.put("CREATER", Jurisdiction.getUsername());
        map.put("BUSINESS_ID", socialSecurityBean.getBusinessId());
        //调用OA发起流程方法；
        Map requestData = new HashMap();
        requestData.put("pd", ConvertUtil.obj2Json(socialSecurityBean));
        requestData.put("map", map);
        requestData.put("processKey", "KEY_social_security");//社保工单流程标识
        requestData.put("idname", "socialSecurityId");
        String sessionId= (String) Jurisdiction.getSession().getId();
        Result<String> result=oaInterfaceForSpringBootService.socialSecurityProcessStart("http://localhost:8080",requestData, sessionId);
        //return HttpUtils.doPostrequest(url,data,sessionId);
        return result.getData();
    }
}
