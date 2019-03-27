package com.mzkj.util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;

/**
 * @Description: vo转do
 * json转对象
 * 对象转json
 * @Author: zw
 * @Date: 2019/3/27 14:42
 * @Version: 1.0
 */
public class ConvertUtil {

    //对象转json
    public static String obj2Json(Object obj) {
        String jsonStr = JSON.toJSONString(obj);
        return jsonStr;
    }

    //json转对象
    public static <T> T json2Obj(String json, Class<T> target) {
        return JSON.parseObject(json, target);
    }

    public static <T> T objectCopyParams(Object obj, Class<T> target) {
        String jsonStr = JSON.toJSONString(obj);
        return JSON.parseObject(jsonStr, target);
    }

}
