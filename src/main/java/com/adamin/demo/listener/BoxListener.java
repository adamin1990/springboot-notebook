package com.adamin.demo.listener;

import com.adamin.demo.entity.Box;
import com.adamin.demo.listener.event.BoxEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Classname BoxListener
 * @Description 自定义box监听器
 * @Date 2022/4/11 11:41
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Component
public class BoxListener implements ApplicationListener<BoxEvent> {
    private static final Logger logger = LoggerFactory.getLogger(BoxListener.class);
    @Override
    public void onApplicationEvent(BoxEvent event) {
        Box box = event.getBox();

        logger.info("----监听到box自定义监听,内容：{}----",box.getContent());



    }
}
