package com.hzg.rabbitmq.pro;

import com.hzg.rabbitmq.pro.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * @Package: PACKAGE_NAME
 * @Description: rabbitmq服务提供者测试类
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-29 14:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProviderTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, "oasis.ocean.whale", "topics: springboot rabbitmq msg");
    }

    /**
     * 确认模式：
     * 1、配置文件yml开启确认模式 publisher-confirms: true
     * 2、设置回调函数rabbitTemplate.setConfirmCallback
     */
    @Test
    public void testConfirm() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * @param correlationData   相关配置信息
             * @param ack               判断交换机是否成功接收消息
             * @param cause             失败原因
             */
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm execute...");
                if (ack) {
                    System.out.println("exchange receive successful");
                } else {
                    System.out.println("exchange receive failed: cause is " + cause);
                    // TODO 发送重试
                }
            }
        });
        rabbitTemplate.convertAndSend(RabbitmqConfig.CONFIRM_EXCHANGE_NAME, "direct-confirm", "direct-confirm: springboot rabbitmq msg");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回退模式：消息发送到交换机，交换机把消息路由到队列失败才会执行ReturnCallback
     * 1、配置文件yml开启确认模式 publisher-returns: true
     * 2、设置回调函数rabbitTemplate.setReturnCallback
     * 3、设置Exchange处理消息的模式
     * 1.如果消息没有路由到Queue，则丢弃消息(默认)
     * 2.如果消息没有路由到Queue，则消息返回给发送方ReturnCallback
     */
    @Test
    public void testReturn() {
        // 设置交换机没有成功将消息路由到Queue时，消息返回给发送方
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * @param message    生产者的原消息
             * @param replyCode  错误码
             * @param replyText  错误信息
             * @param exchange   交换机
             * @param routingKey 路由键
             */
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("return execute...");
                System.out.println("===============>routingKey：" + routingKey);
                System.out.println("===============>msgBody：" + new String(message.getBody()));
                /**错误码和错误信息：com.rabbitmq.client.AMQP*/
                System.out.println("===============>errorInfo：" + replyCode + ":" + replyText);
            }
        });
        rabbitTemplate.convertAndSend(RabbitmqConfig.RETURN_EXCHANGE_NAME, "direct-return", "direct-return: springboot rabbitmq msg");
    }

    /**
     * Consumer Ack
     */
    @Test
    public void testConsumerAck() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.CONFIRM_EXCHANGE_NAME, "direct-confirm", "direct-confirm: springboot rabbitmq msg");
    }

    /**
     * Consumer 限流
     */
    @Test
    public void testConsumerQos() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitmqConfig.CONFIRM_EXCHANGE_NAME, "direct-confirm", "direct-confirm: springboot rabbitmq msg");
        }
    }

    /**
     * Message TTL
     * 单独消息过期
     */
    @Test
    public void testMessageTTL() {
        // 消息设置两秒过期
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("6000");
        Message message = new Message("direct-ttl: springboot rabbitmq msg".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(RabbitmqConfig.TTL_EXCHANGE_NAME, "direct-ttl", message);
    }

    /**
     * Queue TTL
     * 队列过期
     */
    @Test
    public void testQueueTTL() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.TTL_EXCHANGE_NAME, "direct-ttl", "direct-ttl: springboot rabbitmq msg");
    }

    /**
     * DLX 死信交换机：
     * 1、消息过期
     * 2、超过队列消息最大长度
     * 3、消费者拒收消息
     * <p/>
     * 延迟队列 = TTL + DLX
     * 1、定义正常的交换机和队列，队列设置消息过期时间--无消费者监听队列
     * 2、定义用于绑定的死信交换机和死信队列--有消费者监听队列
     * 3、正常队列中的消息过期后通过死信交换机路由到死信队列被消费者消费
     */
    @Test
    public void testDLX() {
        rabbitTemplate.convertAndSend("controller-temp-exchange", "direct-temp", "direct-temp: springboot rabbitmq msg");
        // for (int i = 0; i < 20; i++) {
        //     rabbitTemplate.convertAndSend("controller-temp-exchange", "direct-temp", "direct-temp: springboot rabbitmq msg");
        // }
    }
}
