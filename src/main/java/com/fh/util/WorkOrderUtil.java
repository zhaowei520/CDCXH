package com.fh.util;

import com.alibaba.fastjson.JSON;
import com.mzkj.facade.vo.Result;
import com.mzkj.util.Jurisdiction;
import com.mzkj.vo.general.GeneralContractVo;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: luosc
 * @Description:
 * @Date:created in 15:32 2019-05-29
 */
public class WorkOrderUtil {
    private WorkOrderUtil() {
    }

    public static WorkOrderUtil getInstance() {
        return new WorkOrderUtil();
    }

    /**
     * 根据businessid获取社保工单数据
     * return
     * Author luosc
     * param
     * Date 2019-05-29 15:34
     */
    public static Result<String> findSocialSecurityByCode(String rootPath, String businessId, String sessionId) {
        String path = rootPath + "/workOrder/findByCode?businessId=" + businessId;
        String resultstr = doGetRequest(path, sessionId);
        Result<String> result = JSON.parseObject(resultstr, Result.class);
        return result;
    }

    /**
     * 根据businessId获取数据
     * return
     * Author luosc
     * param
     * Date 2019-06-04 11:05
     */
    public static String findGeneralContractByBusinessId(String rootPath, String businessId, String sessionId) {
        return doGetRequest(rootPath + "/generalContract/findByBusinessId?businessId=" + businessId, sessionId);
    }

    /**
     * 审批流程中保存合同接口
     * return
     * Author luosc
     * param
     * Date 2019-06-04 11:51
     */
    public static String generalContractSave(String rootPath,GeneralContractVo generalContractVo,String sessionId) {
        String jsonString = JSON.toJSONString(generalContractVo);
        return doPostrequest(rootPath + "/generalContract/save", jsonString, sessionId);
    }


    /**
     * 流程中修改合同
     * return
     * Author luosc
     * param
     * Date 2019-06-04 11:57
     */
    public static String generalContractEdit(String rootPath,GeneralContractVo generalContractVo,String sessionId) {
        String jsonString = JSON.toJSONString(generalContractVo);
        return doPostrequest(rootPath + "/generalContract/edit", jsonString, sessionId);
    }

    /**
     * 发起get请求
     * return
     * Author luosc
     * param
     * Date 2019-05-29 15:30
     */
    private static String doGetRequest(String path, String sessionId) {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(path);
        httpGet.setHeader("Cookie", "custom.session=" + sessionId);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(50000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(50000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(50000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();
            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());

//            System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//            System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            result = EntityUtils.toString(responseEntity);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * post请求
     * return
     * Author luosc
     * param
     * Date 2019-06-04 11:48
     */
    public static String doPostrequest(String url, String jsonString, String sessionId) {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        httpPost.setHeader("Cookie", "custom.session=" + sessionId);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            //System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
