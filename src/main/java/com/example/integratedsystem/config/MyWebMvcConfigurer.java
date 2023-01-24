package com.example.integratedsystem.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.example.integratedsystem.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wu Hongjian
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${my-data.login-check}")
    private boolean isLoginCheck;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (isLoginCheck){
            registry.addInterceptor(new SaInterceptor(handler-> StpUtil.checkLogin()))
                    .addPathPatterns("/**")
                    .excludePathPatterns("/"
                            ,"/user/login"
                            ,"/error"
                            ,"/favicon.ico"
                            ,"/static/**");
        }
//        registry.addInterceptor(new RequestInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}
