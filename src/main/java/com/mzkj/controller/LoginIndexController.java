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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Autowired
    private MenuManager menuService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result<List<Menu>> index() {
        Result<List<Menu>> result = new Result<>();
        PageData pd = new PageData();
        pd.put("MENU_ID", "0");
        try {
            List<Menu> menuList = menuService.listAllMenuQx(pd);
            result.setData(menuList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
