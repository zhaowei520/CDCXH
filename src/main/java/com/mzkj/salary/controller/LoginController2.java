package com.mzkj.salary.controller;

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

/**
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */

@RestController
@RequestMapping(value = "/login")
public class LoginController2 {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String submitLogin(String username, String password, HttpServletRequest request) {
        try {
            //UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            HttpSession session1 = request.getSession();
            String id1 = session1.getId();
            Serializable id = session.getId();
            Object tenant_id = session.getAttribute("TENANT_ID_");
            subject.login(null);
        } catch (DisabledAccountException e) {
            request.setAttribute("msg", "账户已被禁用");
            return "login";
        } catch (AuthenticationException e) {
            request.setAttribute("msg", "用户名或密码错误");
            return "login";
        }

        // 执行到这里说明用户已登录成功
        return "redirect:/auth/index";
    }


    @RequestMapping(value = "/login_toLogin", method = RequestMethod.GET)
    public String loginPage() {

        return "login";
    }

}
