package com.adamin.demo.activemq.consumer;

import com.adamin.demo.config.ActiveMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Classname QueueConsumer
 * @Description TODO
 * @Date 2022/4/11 15:18
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Component
public class MsgConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MsgConsumer.class);
    @JmsListener(destination = ActiveMqConfig.QUEUE_NAME)
    public void readQueueMsg(String msg) {
        logger.info("-----activeMq接受队列消息：{}----",msg);

    }
    @JmsListener(destination = ActiveMqConfig.TOPIC_NAME,containerFactory = "topicListenerContainer")
//    @SendTo("return")
    public void readTopicMsg(String message){
        logger.info("-----activeMq接受topic消息：{}----",message);
    }
}
