/*
package com.zhzteam.zhz233.common.config;

import com.zhzteam.zhz233.filter.AuthPathFilter;
import com.zhzteam.zhz233.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean AuthPathFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthPathFilter());
        registration.addUrlPatterns("/*");
        registration.setName("AuthPathFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean CorsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CorsFilter");
        registration.setOrder(1);
        return registration;
    }
}
*/
