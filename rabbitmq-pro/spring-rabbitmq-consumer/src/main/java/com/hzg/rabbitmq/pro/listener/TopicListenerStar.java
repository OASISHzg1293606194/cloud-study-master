package com.hzg.rabbitmq.pro.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @Package: com.hzg.rabbitmq.pro.listener
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-24 10:33
 */
public class TopicListenerStar implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(message.getBody());
    }
}
