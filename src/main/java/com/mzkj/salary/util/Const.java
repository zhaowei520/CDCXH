package com.mzkj.salary.util;

import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 常量定义
 *
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */

public class Const {

    public static final String SESSION_USERNAME = "USERNAME"; // 用户名
    public static final String SESSION_TENANT = "TENANT_ID_"; // 用户名
    public static final String SESSION_U_NAME = "U_NAME"; // 用户姓名
    public static final String SESSION_RNUMBERS = "RNUMBERS"; // 角色编码数组
    public static final String DEPARTMENT_IDS = "DEPARTMENT_IDS"; // 当前用户拥有的最高部门权限集合
    public static final String DEPARTMENT_ID = "DEPARTMENT_ID"; // 当前用户拥有的最高部门权限
    public static final String SESSION_allmenuList = "allmenuList"; // 全部菜单
    public static final String SESSION_QX = "QX"; // 主职角色权限
    public static final String SESSION_QX2 = "QX2"; // 副职角色权限
}
