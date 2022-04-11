package com.adamin.demo.activemq;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @Classname MsgProducer
 * @Description 消息生产/发送者
 * @Date 2022/4/11 14:04
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Service
public class MsgProducer {
    @Resource
    private JmsMessagingTemplate messagingTemplate;

    public void sendMessage(Destination destination,String message){
        messagingTemplate.convertAndSend(destination,message
        );
    }

}
