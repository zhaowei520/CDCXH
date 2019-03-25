package com.mzkj.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description: 覆写shiro UserFilter,只有admin登录账户才能够查看swagger api
 * @Author: zw
 * @Date: 2019/3/19 11:44
 * @Version: 1.0
 */
@Configuration
public class MyUserFilter extends UserFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(this.isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = this.getSubject(request, response);
            if(subject.getPrincipal() != null){
                Session session = subject.getSession();
                String username = (String) session.getAttribute("USERNAME");
                if("admin".equals(username)){
                    return true;
                }
            }
            return false;
        }
    }
}
