package com.mzkj.util;

import com.github.pagehelper.PageHelper;

/**
 * @Description: 分页方法
 * @Author: zw
 * @Date: 2019/3/27 16:26
 * @Version: 1.0
 */
public class PageUtil {
    /**
     * 分页方法
     * return
     * Author luosc
     * param pageNum 当前页，pageSize 每页显示条数
     * Date 2019-03-28 10:26
     */
    public static void startPage(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum * pageSize, pageSize, true);
    }
    /**
     * 传入VoObject,需继承BaseVo
     * return
     * Author luosc
     * param VoObject
     * Date 2019-03-28 10:27
     */
    public static void startPage(Object VoObject){
        PageHelper.startPage(VoObject);
    }

    /**
     * 传入VoObject 将VoObject的分页信息传到pageHelper,并将VoObject 转为target类型
     * return
     * Author luosc
     * param VoObject 源Vo对象，target目标类模板
     * Date 2019-03-28 10:36
     */
    public static <T> T startPageAndObjectCopyParams(Object VoObject,Class<T> target){
        PageHelper.startPage(VoObject);
        return ConvertUtil.objectCopyParams(VoObject, target);
    }

}
