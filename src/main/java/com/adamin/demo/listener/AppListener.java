package com.adamin.demo.listener;

import com.adamin.demo.entity.Box;
import com.adamin.demo.service.BoxService;
import com.adamin.demo.service.impl.BoxServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * @Classname AppListener
 * @Description context初始化和更新监听器
 * @Date 2022/4/11 10:37
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(AppListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("----context refreshed event triggered----");
        ApplicationContext applicationContext = event.getApplicationContext();
        BoxService bean = applicationContext.getBean(BoxService.class);
        Box box = bean.getBox(2);
        ServletContext bean1 = applicationContext.getBean(ServletContext.class);
        bean1.setAttribute("box2",box);
    }
}
