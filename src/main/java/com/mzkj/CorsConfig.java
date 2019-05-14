package com.mzkj;

import com.alibaba.druid.util.StringUtils;
import com.fh.entity.system.User;
import com.mzkj.util.Const;
import com.mzkj.util.Jurisdiction;

import org.apache.shiro.session.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

/**
 * @Description: 跨域处理
 * @Author: zw
 * @Date: 2019/3/19 10:45
 * @Version: 1.0
 */
@Configuration
public class CorsConfig extends HandlerInterceptorAdapter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getServletPath();

        //前端react项目的域名
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        //设置允许访问cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(request, res);
        }
    }

    /**
     * 获取权限
     * return
     * Author luosc
     * param
     * Date 2019-05-09 14:42
     */
public static void getQX(HttpServletResponse response ,HttpServletRequest request,String path) throws IOException {
//    Session session=Jurisdiction.getSession();
//    String params = request.getQueryString();
//    if (!StringUtils.isEmpty(params)) {
//        path = path + "?" + params;
//    }
//    boolean b = Jurisdiction.hasJurisdiction(path); // 访问权限校验
//    if (!b) {
//        response.sendRedirect(request.getContextPath() + Const.LOGIN);
//    }
}
    @Override
    public void destroy() {

    }
}
