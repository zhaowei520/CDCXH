package com.mzkj.controller;

import com.mzkj.controller.insurance.SocialSecurityController;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.insurance.SocialSecurityManager;
import com.mzkj.vo.insurance.SocialSecurityQueryVo;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 工单对外提供接口controller
 * @Author: luosc
 * @Description:
 * @Date:created in 15:25 2019-05-29
 */
@RestController
@RequestMapping(value = "/workOrder")
public class WorkOrderApiController {
    private static Logger logger = LogManager.getLogger(SocialSecurityController.class);
    @Autowired
    private SocialSecurityManager socialSecurityService;
    public SocialSecurityManager getSocialSecurityService() {
        return socialSecurityService;
    }
    /**
     * 根据businessId查询数据
     * return
     * Author luosc
     * param
     * Date 2019-05-13 15:46
     */
    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ResponseBody
    public Result<SocialSecurityQueryVo> findByCode(String businessId) {
        Result<SocialSecurityQueryVo> result = new Result<>();
        try {
            SocialSecurityQueryVo socialSecurityQueryVo = getSocialSecurityService().findByCode(businessId);
            result.setData(socialSecurityQueryVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        result.setSuccess(true);
        return result;
    }
}
