package com.mzkj.util;

import com.alibaba.druid.util.StringUtils;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author: luosc
 * @Description:
 * @Date:created in 11:46 2019-05-28
 */
public class PropertiesUtil {

    private static Properties property;

    // trim()方法=>去掉字符串两边到空格
    public static String getProperty(String filename,String key) {
        // 配置文件路径
        //filename = "config.properties";
        property = new Properties();
        try {
            // 加载配置文件
            property.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(filename)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String value = property.getProperty(key.trim());
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return value.trim();
    }
//
//    public static void main(String[] args) {
//        System.out.println(getProperty("application.properties", "oa.server.url"));
//    }
}
