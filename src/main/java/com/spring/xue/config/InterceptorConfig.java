package com.spring.xue.config;

import com.spring.xue.interceptor.JwtLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> filterList = new ArrayList<String>();
        filterList.add("/");
        registry.addInterceptor(jwtLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");// 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
    }

    @Bean
    public JwtLoginInterceptor jwtLoginInterceptor(){
        return new JwtLoginInterceptor();
    }
}
