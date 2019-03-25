package com.mzkj.salary.controller;

import com.fh.entity.system.Menu;
import com.fh.util.PageData;
import com.mzkj.salary.util.Const;
import com.mzkj.salary.util.Jurisdiction;
import com.mzkj.salary.vo.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
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
