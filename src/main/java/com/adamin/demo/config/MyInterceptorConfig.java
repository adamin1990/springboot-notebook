package com.adamin.demo.config;

import com.adamin.demo.intercepter.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname MyInterceptorConfig
 * @Description 拦截器的配置
 * @Date 2022/4/11 12:20
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }
}
