package com.mzkj.service.oainterface.impl;

import com.fh.controller.springboot.trunkservice.OaInterfaceForSpringBootService;
import com.mzkj.bean.SocialSecurityBean;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.oainterface.ProcessInterface;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: luosc
 * @Description:
 * @Date:created in 9:50 2019-05-29
 */
@Service("processInterfaceService")
public class ProcessInterfaceService implements ProcessInterface{
    public Properties getPprVue() throws Exception{
        InputStream inputStream = ProcessInterfaceService.class.getClassLoader().getResourceAsStream("application.properties");
        Properties p = new Properties();
        p.load(inputStream);
        inputStream.close();
        return p;
    }
    @Override
    public String processStart(SocialSecurityBean socialSecurityBean) throws Exception {
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
        String rootPath = getPprVue().getProperty("oa.server.url");
        OaInterfaceForSpringBootService oaInterfaceForSpringBootService = OaInterfaceForSpringBootService.getInstance();
        Result<String> result=oaInterfaceForSpringBootService.socialSecurityProcessStart(rootPath, requestData, sessionId);
        return result.getData();
    }


}
