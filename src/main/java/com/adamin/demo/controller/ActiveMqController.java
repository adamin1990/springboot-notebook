package com.adamin.demo.controller;

import com.adamin.demo.activemq.MsgProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @Classname ActiveMqController
 * @Description TODO
 * @Date 2022/4/11 15:01
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@RestController
@RequestMapping("/amq")
public class ActiveMqController {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMqController.class);
    @Resource
    private MsgProducer msgProducer;
    @Resource(name = "topic")
    private Destination destination;
    @Resource(name = "queue")
    private Destination queueDestination;
    @RequestMapping("/sendTopicMsg/{msg}")
    public String sendTopicMsg(@PathVariable("msg") String message){
        msgProducer.sendMessage(destination,message);

        return  message;

    }

    @RequestMapping("/sendQueueMsg/{msg}")
    public String sendQueueMsg(@PathVariable("msg") String message){
        msgProducer.sendMessage(queueDestination,message);

        return  message;

    }
}
