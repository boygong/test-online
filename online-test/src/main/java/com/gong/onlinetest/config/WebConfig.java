package com.gong.onlinetest.config;


import com.gong.onlinetest.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/admin", "/teacher/teacherLogin","/doc.html/**","/swagger-resources/**","/swagger-ui/**", "/v3/**", "/error");
//    }
}

