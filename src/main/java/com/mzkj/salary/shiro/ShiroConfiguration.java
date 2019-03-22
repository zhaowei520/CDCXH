//package com.packt.cardatabase.shiro;
//
//import RedisShiroSessionManager;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.controller.ShiroFilterFactoryBean;
//import org.apache.shiro.controller.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.controller.servlet.SimpleCookie;
//import org.apache.shiro.controller.session.mgt.DefaultWebSessionManager;
//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
//import org.crazycake.shiro.RedisSessionDAO;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.boot.controller.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.controller.filter.DelegatingFilterProxy;
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//
//@Configuration
//public class ShiroConfiguration {
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        // 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
//        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        return filterRegistration;
//    }
//
//    @Bean(name = "shiroFilter")
//    public ShiroFilterFactoryBean shiroFilter() {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager());
//        // 没有登陆的用户只能访问登陆页面
//        shiroFilterFactoryBean.setLoginUrl("/auth/login");
//        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/auth/index");
//        //自定义拦截器
//        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
//        // 权限控制map.
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        filterChainDefinitionMap.put("/login", "anon");
////        filterChainDefinitionMap.put("/api/**", "anon");
//        filterChainDefinitionMap.put("/car/cars", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 设置realm.
//        securityManager.setRealm(myShiroRealm());
//        // 自定义缓存实现 使用redis
//        securityManager.setCacheManager(cacheManager());
//        // 自定义session管理 使用redis
//        securityManager.setSessionManager(sessionManager());
//        return securityManager;
//    }
//
//    /**
//     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
//     *
//     * @return
//     */
//    @Bean
//    public ShiroRealm myShiroRealm() {
//        ShiroRealm myShiroRealm = new ShiroRealm();
//        return myShiroRealm;
//    }
//
//    @Bean
//    public SimpleCookie simpleCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie("custom.session");
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setPath("/");
//        simpleCookie.setMaxAge(-1);
//        return simpleCookie;
//    }
//
//    /**
//     * cacheManager 缓存 redis实现
//     * 使用的是shiro-redis开源插件
//     *
//     * @return
//     */
//    @Bean
//    public RedisCacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        redisCacheManager.setKeyPrefix("shiro_redis_session:");
//        return redisCacheManager;
//    }
//
//    /**
//     * 配置shiro redisManager
//     * 使用的是shiro-redis开源插件
//     *
//     * @return
//     */
//    @Bean
//    public RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost("192.168.50.133");
//        redisManager.setPassword("12345");
//        redisManager.setPort(6379);
//        redisManager.setExpire(300000000);// 配置缓存过期时间
//        redisManager.setTimeout(100000);
//        // redisManager.setPassword(password);
//        return redisManager;
//    }
//
//    /**
//     * Session Manager
//     * 使用的是shiro-redis开源插件
//     */
//    @Bean
//    public DefaultWebSessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(redisSessionDAO());
//        sessionManager.setSessionIdCookie(simpleCookie());
//        return sessionManager;
//    }
//
//    /**
//     * RedisSessionDAO shiro sessionDao层的实现 通过redis
//     * 使用的是shiro-redis开源插件
//     */
//    @Bean
//    public EnterpriseCacheSessionDAO redisSessionDAO() {
//        EnterpriseCacheSessionDAO redisSessionDAO = new EnterpriseCacheSessionDAO();
//        return redisSessionDAO;
//    }
//
//    /**
//     * 限制同一账号登录同时登录人数控制
//     *
//     * @return
//     */
////    @Bean
////    public KickoutSessionControlFilter kickoutSessionControlFilter() {
////        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
////        kickoutSessionControlFilter.setCacheManager(cacheManager());
////        kickoutSessionControlFilter.setSessionManager(sessionManager());
////        kickoutSessionControlFilter.setKickoutAfter(false);
////        kickoutSessionControlFilter.setMaxSession(1);
////        kickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
////        return kickoutSessionControlFilter;
////    }
//
//
//    /***
//     * 授权所用配置
//     *
//     * @return
//     */
//    @Bean
//    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    /***
//     * 使授权注解起作用不如不想配置可以在pom文件中加入
//     * <dependency>
//     *<groupId>org.springframework.boot</groupId>
//     *<artifactId>spring-boot-starter-aop</artifactId>
//     *</dependency>
//     * @param securityManager
//     * @return
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//    /**
//     * Shiro生命周期处理器
//     *
//     */
//    @Bean
//    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//}
