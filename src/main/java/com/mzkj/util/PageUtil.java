package com.mzkj.util;

import com.github.pagehelper.PageHelper;

/**
 * @Description: 分页方法
 * @Author: zw
 * @Date: 2019/3/27 16:26
 * @Version: 1.0
 */
public class PageUtil {
    public static void startPage(Integer pageIndex, Integer pageSise){
        PageHelper.startPage(pageIndex * pageSise, pageSise, true);
    }
}
