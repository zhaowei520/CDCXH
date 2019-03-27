package com.mzkj.vo;

import com.fh.entity.system.Menu;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Description: 菜单vo
 * @Author: zw
 * @Date: 2019/3/26 17:12
 * @Version: 1.0
 */
public class MenuVo extends Page{
    private String MENU_ID;    //菜单ID
    private String MENU_NAME;  //菜单名称
    private String MENU_URL;  //链接
    private String PARENT_ID;  //上级菜单ID
    private String MENU_ORDER;  //排序
    private String MENU_ICON;  //图标
    private int MENU_CLASSIFICATION;    //菜单分类
    private String TENANT_ID_;
    private Menu parentMenu;
    private List<Menu> subMenu;
    private boolean hasMenu = false;

    public String getMENU_ID() {
        return MENU_ID;
    }

    public void setMENU_ID(String MENU_ID) {
        this.MENU_ID = MENU_ID;
    }

    public String getMENU_NAME() {
        return MENU_NAME;
    }

    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME;
    }

    public String getMENU_URL() {
        return MENU_URL;
    }

    public void setMENU_URL(String MENU_URL) {
        this.MENU_URL = MENU_URL;
    }

    public String getPARENT_ID() {
        return PARENT_ID;
    }

    public void setPARENT_ID(String PARENT_ID) {
        this.PARENT_ID = PARENT_ID;
    }

    public String getMENU_ORDER() {
        return MENU_ORDER;
    }

    public void setMENU_ORDER(String MENU_ORDER) {
        this.MENU_ORDER = MENU_ORDER;
    }

    public String getMENU_ICON() {
        return MENU_ICON;
    }

    public void setMENU_ICON(String MENU_ICON) {
        this.MENU_ICON = MENU_ICON;
    }

    public int getMENU_CLASSIFICATION() {
        return MENU_CLASSIFICATION;
    }

    public void setMENU_CLASSIFICATION(int MENU_CLASSIFICATION) {
        this.MENU_CLASSIFICATION = MENU_CLASSIFICATION;
    }

    public String getTENANT_ID_() {
        return TENANT_ID_;
    }

    public void setTENANT_ID_(String TENANT_ID_) {
        this.TENANT_ID_ = TENANT_ID_;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public List<Menu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<Menu> subMenu) {
        this.subMenu = subMenu;
    }

    public boolean isHasMenu() {
        return hasMenu;
    }

    public void setHasMenu(boolean hasMenu) {
        this.hasMenu = hasMenu;
    }
}
