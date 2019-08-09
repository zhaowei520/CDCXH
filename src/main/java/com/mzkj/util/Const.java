package com.mzkj.util;

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
    public static final String LOGIN = "/index/login_toLogin";//登录方法
    public static final String FILEPATHFILE = "uploadFiles/file/"; // 文件上传路径
    public static final String NO_INTERCEPTOR_PATH =
            ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)|(uploadImgs)|(fileServerDir)|(mp)).*"; // 不对匹配该值的访问路径拦截（正则）
    public static final String SESSION_USER = "sessionUser"; // session用的用户
    // 状态
    public static final int STATUS_0 = 0;
    public static final int STATUS_1 = 1;
    public static final int STATUS_2 = 2;
    public static final int STATUS_3 = 3;

    //菜单系统类型
    public static final int MENU_CLASSIFICATION_SPRINGBOOT = 2;

    //原件流转状态
    public static final String ORIGINAL_OUT_STATUS_1 = "1";//出库中
    public static final String ORIGINAL_OUT_STATUS_2 = "2";//入库
    public static final String ORIGINAL_OUT_STATUS_3 = "3";//待借入
    public static final String ORIGINAL_OUT_STATUS_4 = "4";//被驳回

    //原件持有状态
    public static final String ORIGINAL_HOLD_STATUS_0 = "0";//无
    public static final String ORIGINAL_HOLD_STATUS_1 = "1";//在客户处
    public static final String ORIGINAL_HOLD_STATUS_2 = "2";//在公司内部

    //原件操作类型
    public static final String LOAN_OUT = "loanOut";
    public static final String LOAN_OUT_CONFIRMED = "loanOutConfirmed";
    public static final String REJECT = "reject";
    public static final String LOAN_IN = "loanIn";


    //数据结构
    public static final String HTML_LEFT_P = "<p>";
    public static final String  HTML_BR= "<br/>";
    public static final String HTML_RIGHT_P = "</p>";

    public static final String DATA_FH = ",fh,";
    public static final String DATA_LEFT_BORDER = "[";
    public static final String DATA_RIGHT_BORDER = "]";

    //树的顶层节点id
    public static final String ROOF_ID_FOR_TREE = "ROOF";

}
