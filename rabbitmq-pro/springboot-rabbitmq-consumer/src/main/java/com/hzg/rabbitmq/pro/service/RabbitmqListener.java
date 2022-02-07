package com.hzg.rabbitmq.pro.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Package: com.hzg.rabbitmq.pro.service
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-29 15:47
 */
@Component
public class RabbitmqListener {

    @RabbitListener(queues = "springboot-topic-queue")
    public void rabbitmqListener(Message message) {
        System.out.println(message);
        System.out.println(new String(message.getBody()));
    }

    // @RabbitListener(queues = "springboot-direct-confirm-queue")
    public void rabbitmqListenerConfirm(Message message) {
        // System.out.println(message);
        // System.out.println(new String(message.getBody()));
    }

    @RabbitListener(queues = "springboot-direct-return-queue")
    public void rabbitmqListenerReturn(Message message) {
        System.out.println(message);
        System.out.println(new String(message.getBody()));
    }

    // @RabbitListener(queues = "springboot-direct-confirm-queue")
    public void rabbitmqListenerConsumerAck(Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.println(message);
            System.out.println(new String(message.getBody()));
            // int i = 3 / 0;
            /**
             * 手动签收
             *
             * @param deliveryTag 消息标签
             * @param multiple    true允许多个消息同时签收，false仅确认提供的交付标签
             */
            System.out.println(channel.isOpen());
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            try {
                /**
                 * 拒绝签收
                 *
                 * @param deliveryTag 消息标签
                 * @param multiple    true允许多个消息同时签收，false仅确认提供的交付标签
                 * @param requeue     消息重新回到queue，broker会重新发送该消息
                 */
                channel.basicNack(deliveryTag, false, true);

                // 处理单条消息重回队列
                // channel.basicReject(deliveryTag, true);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = "springboot-direct-confirm-queue")
    public void rabbitmqListenerConsumerQos(Message message, Channel channel) throws Exception {
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        /**
         * 手动签收
         *
         * @param deliveryTag 消息标签
         * @param multiple    true允许多个消息同时签收，false仅确认提供的交付标签
         */
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }


    // @RabbitListener(queues = "test-temp-queue")
    public void rabbitmqListenerTemp(Message message, Channel channel) throws Exception {
        System.out.println("~~~~~~~~~~~~~~~temp正常消费~~~~~~~~~~~~~~~");
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        System.out.println("~~~~~~~~~~~~~~~消息拒收~~~~~~~~~~~~~~~");
        // 模拟消费者拒收，消息不重回队列
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }

    @RabbitListener(queues = "test-dlx-queue")
    public void rabbitmqListenerDlx(Message message, Channel channel) throws Exception {
        System.out.println("~~~~~~~~~~~~~~~DLX死信交换机消费~~~~~~~~~~~~~~~");
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
