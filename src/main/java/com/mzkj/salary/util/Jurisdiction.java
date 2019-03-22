package com.mzkj.salary.util;

import com.fh.entity.system.Menu;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.List;
import java.util.Map;

/**
 * @Description: shiro session获取
 * @Author: zw
 * @Date: 2019/3/19 11:44
 * @Version: 1.0
 */
public class Jurisdiction {


    /**
     * 获取当前登录的用户名
     *
     * @return
     */
    public static String getUsername() {
        return getSession().getAttribute(Const.SESSION_USERNAME).toString();
    }

    /**
     * 获取当前登录的用户姓名
     *
     * @return
     */
    public static String getU_name() {
        return getSession().getAttribute(Const.SESSION_U_NAME).toString();
    }

    /**
     * 获取当前登录用户的角色编码
     *
     * @return
     */
    public static String getRnumbers() {
        return getSession().getAttribute(Const.SESSION_RNUMBERS).toString();
    }

    /**
     * 获取用户的最高组织机构权限集合
     *
     * @return
     */
    public static String getDEPARTMENT_IDS() {
        return getSession().getAttribute(Const.DEPARTMENT_IDS).toString();
    }

    /**
     * 获取用户的最高组织机构权限
     *
     * @return
     */
    public static String getDEPARTMENT_ID() {
        return getSession().getAttribute(Const.DEPARTMENT_ID).toString();
    }

    /**
     * 获取当前登录的用户的租户
     *
     * @return
     */
    public static String getTenant() {
        return getSession().getAttribute(Const.SESSION_TENANT).toString();
    }

    /**
     * shiro管理的session
     *
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }


    /**
     * 按钮权限(方法中校验)
     *
     * @param menuUrl 菜单路径
     * @param type    类型(add、del、edit、cha)
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean buttonJurisdiction(String menuUrl, String type) {
        //判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
        /**
         * 根据点击的菜单的xxx.do去菜单中的URL去匹配，当匹配到了此菜单，判断是否有此菜单的权限，没有的话跳转到404页面
         * 根据按钮权限，授权按钮(当前点的菜单和角色中各按钮的权限匹对)
         */
        String USERNAME = getUsername();  //获取当前登录者loginname
        Session session = getSession();
        List<Menu> menuList =
            (List<Menu>) session.getAttribute(USERNAME + Const.SESSION_allmenuList); //获取菜单列表
        return readMenuButton(menuList, menuUrl, session, USERNAME, type);
    }

    /**
     * 校验按钮权限(递归处理)
     *
     * @param menuList:传入的总菜单(设置菜单时，.do前面的不要重复)
     * @param menuUrl:访问地址
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean readMenuButton(List<Menu> menuList, String menuUrl, Session session, String USERNAME, String type) {
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getMENU_URL().split(".do")[0].equals(menuUrl.split(".do")[0])) { //访问地址与菜单地址循环匹配，如何匹配到就进一步验证，如果没匹配到就不处理(可能是接口链接或其它链接)
                if (!menuList.get(i).isHasMenu()) {        //判断有无此菜单权限
                    return false;
                } else {                      //按钮判断
                    Map<String, String> map = (Map<String, String>) session.getAttribute(
                        USERNAME + Const.SESSION_QX);//主职角色按钮权限(增删改查)
                    Map<String, List<String>> maps =
                        (Map<String, List<String>>) session.getAttribute(
                            USERNAME + Const.SESSION_QX2);//副职角色按钮权限(增删改查)
                    String MENU_ID = menuList.get(i).getMENU_ID();
                    Boolean isAdmin = "admin".equals(USERNAME);
                    if (isAdmin) {
                        return true;
                    } else {
                        Boolean badd = false;
                        Boolean bdel = false;
                        Boolean bedit = false;
                        Boolean bcha = false;
                        if ("add".equals(type)) {
                            badd = RightsHelper.testRights(map.get("adds"), MENU_ID);
                            if (!badd) {
                                List<String> addsList = maps.get("addsList");
                                if (null != addsList) {
                                    for (int n = 0; n < addsList.size(); n++) {
                                        badd = RightsHelper.testRights(addsList.get(n), MENU_ID);
                                        if (badd)
                                            break;
                                    }
                                }
                            }
                            return badd;
                        } else if ("del".equals(type)) {
                            bdel = RightsHelper.testRights(map.get("dels"), MENU_ID);
                            if (!bdel) {
                                List<String> delsList = maps.get("delsList");
                                if (null != delsList) {
                                    for (int n = 0; n < delsList.size(); n++) {
                                        bdel = RightsHelper.testRights(delsList.get(n), MENU_ID);
                                        if (bdel)
                                            break;
                                    }
                                }
                            }
                            return bdel;
                        } else if ("edit".equals(type)) {
                            bedit = RightsHelper.testRights(map.get("edits"), MENU_ID);
                            if (!bedit) {
                                List<String> editsList = maps.get("editsList");
                                if (null != editsList) {
                                    for (int n = 0; n < editsList.size(); n++) {
                                        bedit = RightsHelper.testRights(editsList.get(n), MENU_ID);
                                        if (bedit)
                                            break;
                                    }
                                }
                            }
                            return bedit;
                        } else if ("cha".equals(type)) {
                            bcha = RightsHelper.testRights(map.get("chas"), MENU_ID);
                            if (!bcha) {
                                List<String> chasList = maps.get("chasList");
                                if (null != chasList) {
                                    for (int n = 0; n < chasList.size(); n++) {
                                        bcha = RightsHelper.testRights(chasList.get(n), MENU_ID);
                                        if (bcha)
                                            break;
                                    }
                                }
                            }
                            return bcha;
                        }
                    }
                }
            } else {
                if (!readMenuButton(menuList.get(i).getSubMenu(), menuUrl, session, USERNAME, type)) {//继续排查其子菜单
                    return false;
                }
            }
        }
        return true;
    }
}
