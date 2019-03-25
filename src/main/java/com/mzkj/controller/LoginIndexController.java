package com.mzkj.controller;

import com.fh.entity.system.Menu;
import com.mzkj.util.Const;
import com.mzkj.util.Jurisdiction;
import com.mzkj.vo.Result;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */

@RestController
@RequestMapping(value = "/index")
public class LoginIndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result<List<Menu>> submitLogin() {
        Result<List<Menu>> result = new Result<>();
        Session session = Jurisdiction.getSession();
        List<Menu> allmenuList = (List<Menu>) session.getAttribute(
            Jurisdiction.getUsername() + Const.SESSION_allmenuList);
        result.setData(allmenuList);
        return result;
    }
}
