package com.example.user.fileter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @Author: 王能顺
 * @Date: 2018-8-23 11:59:35
 *
 *配置过滤器的优先级
 */
@Configuration
public class WebComponentConfig {
    @Bean
    public FilterRegistrationBean
    someFilterRegistration1() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加我们写好的过滤器
        registration.setFilter( new LoginFileter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        //设置过滤器顺序
        registration.setOrder(1);
        return registration;
    }
}
