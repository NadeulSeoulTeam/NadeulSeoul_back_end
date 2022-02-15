package com.alzal.nadeulseoulbackend.global.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    public FilterRegistrationBean<BeforeLoginTokenFilter> beforeLoginTokenFilter(){
        FilterRegistrationBean<BeforeLoginTokenFilter> bean = new FilterRegistrationBean<>();
        bean.addUrlPatterns("/users/signin");
        bean.setOrder(0);
        return bean;
    }
}
