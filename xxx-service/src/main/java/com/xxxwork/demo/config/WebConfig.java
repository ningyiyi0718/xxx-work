package com.xxxwork.demo.config;

import com.xxxwork.demo.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ElevenYang
 * @Description: web配置，gateway下其实可以不用要
 * @Date 2023/4/21 22:12
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 拦截所有的请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/*");
    }
}
