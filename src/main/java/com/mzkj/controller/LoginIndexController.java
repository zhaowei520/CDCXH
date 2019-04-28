package com.mzkj.controller;

import com.fh.entity.system.Menu;
import com.fh.util.PageData;
import com.mzkj.controller.template.TemplateController;
import com.mzkj.service.system.menu.MenuManager;
import com.mzkj.util.Const;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */

@RestController
@RequestMapping(value = "/index")
@Api(tags = "LoginIndexController", description = "后台初始化接口")
public class LoginIndexController {

    private static Logger logger = LogManager.getLogger(LoginIndexController.class);

    @RequestMapping(value = "/init_menu", method = RequestMethod.GET)
    @ApiOperation(value = "获取菜单信息", notes = "获取菜单信息")
    public Result<List<Menu>> initMenu() {
        logger.info(Jurisdiction.getUsername() + "获取菜单信息");
        Result<List<Menu>> result = new Result<>();
        Session session = Jurisdiction.getSession();
        List<Menu> menuList = (List<Menu>) session.getAttribute(
            Jurisdiction.getUsername() + Const.SESSION_allmenuList);
        //过滤菜单
        List<Menu> menuSpringBootList = this.selectTypeMenuByMenuClassification(menuList);
        result.setData(menuSpringBootList);
        return result;
    }

    @RequestMapping(value = "/login_toLogin", method = RequestMethod.GET)
    @ApiOperation(value = "权限认证失败", notes = "权限认证失败")
    public Result<PageData> login_toLogin() {
        Result<PageData> result = new Result<>();
        result.setMsg("session失效，请重新oa登录");
        result.setSuccess(false);
        result.setStatus(HttpCode.UNAUTHORIZED.getCode());
        return result;
    }

    private List<Menu> selectTypeMenuByMenuClassification(List<Menu> menuList) {
        List<Menu> menuSpringBootList = new ArrayList<>();
        if (menuList != null && menuList.size() > 0) {
            for (Menu menu : menuList) {
                //过滤,只需要springboot项目的菜单
                if (Const.MENU_CLASSIFICATION_SPRINGBOOT == menu.getMENU_CLASSIFICATION()) {
                    menuSpringBootList.add(menu);
                }
            }
        }
        return menuSpringBootList;
    }
}
