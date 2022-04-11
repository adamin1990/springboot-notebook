package com.adamin.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname RequestListener
 * @Description 请求监听
 * @Date 2022/4/11 11:28
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Component
public class RequestListener implements ServletRequestListener {
    private static final Logger logger = LoggerFactory.getLogger(RequestListener.class);
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        StringBuffer requestURL = servletRequest.getRequestURL();
        logger.info("-----监听请求地址销毁{}----",requestURL);

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        StringBuffer requestURL = servletRequest.getRequestURL();
        logger.info("-----监听请求地址初始化{}----",requestURL);
    }
}
