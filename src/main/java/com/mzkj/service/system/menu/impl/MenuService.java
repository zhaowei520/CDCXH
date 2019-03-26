package com.mzkj.service.system.menu.impl;

import com.fh.entity.system.Menu;
import com.mzkj.mapper.menu.MenuMapper;
import com.mzkj.util.Const;
import com.mzkj.util.Jurisdiction;
import com.fh.util.PageData;
import com.mzkj.service.system.menu.MenuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明： 菜单服务
 * 创建人：zw
 * 创建时间：2019-03-26
 */
@Service("menuService")
public class MenuService implements MenuManager {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 通过ID获取其子一级菜单
     *
     * @param pd
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Menu> listSubMenuByParentId(PageData pd) throws Exception {
        pd.put("parentId", pd.get("MENU_ID"));
        pd.put("username", Jurisdiction.getUsername());
        pd.put("TENANT_ID_", Jurisdiction.getTenant());
        pd.put("MENU_CLASSIFICATION", Const.STATUS_2);
        return (List<Menu>) menuMapper.listSubMenuByParentId(pd);
    }

    @Override
    public List<Menu> listAllMenuQx(PageData pd) throws Exception{
        List<Menu> menuList = this.listSubMenuByParentId(pd);
        for (Menu menu : menuList) {
            pd.put("MENU_ID", menu.getMENU_ID());
            menu.setSubMenu(this.listAllMenuQx(pd));
            menu.setTarget("treeFrame");
        }
        return menuList;
    }


}
