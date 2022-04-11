package com.adamin.demo.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Classname MyInterCepter
 * @Description TODO
 * @Date 2022/4/11 12:10
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       if(handler instanceof HandlerMethod){
           HandlerMethod handlerMethod= (HandlerMethod) handler;
           Method method = handlerMethod.getMethod();
           UnInterceptor annotation = method.getAnnotation(UnInterceptor.class);
           if(null!=annotation){
               logger.info("---不拦截{}方法--",method.getName());
               return  true;

           }
           logger.info("---拦截{}方法--",method.getName());

       }else if(handler instanceof ResourceHttpRequestHandler){
           ResourceHttpRequestHandler resourceHttpRequestHandler= (ResourceHttpRequestHandler) handler;
           logger.info("----拦截资源加载----");

       }



        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
