package com.alzal.nadeulseoulbackend.global.config;

import com.alzal.nadeulseoulbackend.global.filter.BeforeLoginTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

@Configuration
public class FilterConfig {

    public FilterRegistrationBean<BeforeLoginTokenFilter> beforeLoginTokenFilter(){
        FilterRegistrationBean<BeforeLoginTokenFilter> bean = new FilterRegistrationBean<>();
        bean.addUrlPatterns("/users/signin");
        bean.setOrder(0);
        return bean;
    }
}
