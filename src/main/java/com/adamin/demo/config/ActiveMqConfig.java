package com.adamin.demo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * @Classname ActiveMqConfig
 * @Description ActiveMq配置
 * @Date 2022/4/11 14:00
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Configuration
public class ActiveMqConfig {
    /**
     * 发布订阅模式名称
     */
    public static final String TOPIC_NAME="activemq.topic";
    /**
     * 点对点模式名称
     */
    public static final String QUEUE_NAME="activemq.queue";
    @Bean
    public Destination topic(){
        return  new ActiveMQTopic(TOPIC_NAME);
    }
    @Bean
    public  Destination queue(){
        return  new ActiveMQQueue(QUEUE_NAME);
    }


    @Bean
    public JmsListenerContainerFactory topicListenerContainer() {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory();
        DefaultJmsListenerContainerFactory factory = new
                DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
// 相当于在application.yml中配置：spring.jms.pub-sub-domain=true
        factory.setPubSubDomain(true);
        return factory;
    }

}
