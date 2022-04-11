package com.adamin.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Classname HttpListener
 * @Description http session监听
 * @Date 2022/4/11 11:22
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Component
public class HttpListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(HttpListener.class);
    private Integer count=0;
    @Override
    public synchronized void sessionCreated(HttpSessionEvent se) {
        logger.info("----新用户上线----");
        count++;
        se.getSession().getServletContext().setAttribute("count",count);

    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent se) {
        logger.info("---下线----");
        count--;
        se.getSession().getServletContext().setAttribute("count",count);

    }
}
