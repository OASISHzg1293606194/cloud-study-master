package com.hzg.rabbitmq.pro.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @Package: com.hzg.rabbitmq.pro.config
 * @Description: rabbitmq配置类
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-29 13:27
 */
@Configuration
public class RabbitmqConfig {

    public static final String EXCHANGE_NAME = "springboot-topic-exchange";
    public static final String QUEUE_NAME = "springboot-topic-queue";


    /**
     * 交换机
     */
    @Bean("bootExchange")
    public Exchange bootExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 队列
     */
    @Bean("bootQueue")
    public Queue bootQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    /**
     * 队列和交换机绑定关系
     */
    @Bean
    public Binding binding(@Qualifier("bootQueue") Queue queue, @Qualifier("bootExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("oasis.ocean.#").noargs();
    }


    /********************************确认模式confirm********************************/
    public static final String CONFIRM_EXCHANGE_NAME = "springboot-direct-confirm-exchange";
    public static final String CONFIRM_QUEUE_NAME = "springboot-direct-confirm-queue";

    /**
     * 交换机
     */
    @Bean("bootConfirmExchange")
    public Exchange bootConfirmExchange() {
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 队列
     */
    @Bean("bootConfirmQueue")
    public Queue bootConfirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    /**
     * 队列和交换机绑定关系
     */
    @Bean
    public Binding bindingConfirm(@Qualifier("bootConfirmQueue") Queue queue, @Qualifier("bootConfirmExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("direct-confirm").noargs();
    }


    /********************************回退模式return********************************/
    public static final String RETURN_EXCHANGE_NAME = "springboot-direct-return-exchange";
    public static final String RETURN_QUEUE_NAME = "springboot-direct-return-queue";

    /**
     * 交换机
     */
    @Bean("bootReturnExchange")
    public Exchange bootReturnExchange() {
        return ExchangeBuilder.directExchange(RETURN_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 队列
     */
    @Bean("bootReturnQueue")
    public Queue bootReturnQueue() {
        return QueueBuilder.durable(RETURN_QUEUE_NAME).build();
    }

    /**
     * 队列和交换机绑定关系
     */
    @Bean
    public Binding bindingReturn(@Qualifier("bootReturnQueue") Queue queue, @Qualifier("bootReturnExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("direct-return").noargs();
    }

    /********************************TTL 过期时间********************************/
    public static final String TTL_EXCHANGE_NAME = "test-ttl-exchange";
    public static final String TTL_QUEUE_NAME = "test-ttl-queue";

    /**
     * 交换机
     */
    @Bean("bootTtlExchange")
    public Exchange bootTtlExchange() {
        return ExchangeBuilder.directExchange(TTL_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 队列--设置队列中消息过期时间10s
     */
    @Bean("bootTtlQueue")
    public Queue bootTtlQueue() {
        // x-message-ttl 参数key看rabbitmq管理界面
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 10000);
        return QueueBuilder.durable(TTL_QUEUE_NAME).withArguments(arguments).build();
    }

    /**
     * 队列和交换机绑定关系
     */
    @Bean
    public Binding bindingTtl(@Qualifier("bootTtlQueue") Queue queue, @Qualifier("bootTtlExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("direct-ttl").noargs();
    }

    /********************************DLX 死信交换机********************************/
    public static final String DLX_EXCHANGE_NAME = "test-dlx-exchange";
    public static final String DLX_QUEUE_NAME = "test-dlx-queue";
    public static final String DLX_ROUTING_KEY = "direct-dlx";

    /**
     * 死信交换机
     */
    @Bean("bootDlxExchange")
    public Exchange bootDlxExchange() {
        return ExchangeBuilder.directExchange(DLX_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 死信交换机绑定的队列
     */
    @Bean("bootDlxQueue")
    public Queue bootDlxQueue() {
        return QueueBuilder.durable(DLX_QUEUE_NAME).build();
    }

    /**
     * 死信交换机：队列和交换机绑定关系
     */
    @Bean
    public Binding bindingDlx(@Qualifier("bootDlxQueue") Queue queue, @Qualifier("bootDlxExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DLX_ROUTING_KEY).noargs();
    }

    /********************************用于绑定死信交换机的队列********************************/
    @Bean("bootTempExchange")
    public Exchange bootTempExchange() {
        return ExchangeBuilder.directExchange("test-temp-exchange").durable(true).build();
    }

    @Bean("bootTempQueue")
    public Queue bootTempQueue() {
        HashMap<String, Object> arguments = new HashMap<>();
        // 设置队列消息过期时间和队列消息长度作为消息成为死信的条件
        // 设置队列中消息的过期时间10s
        arguments.put("x-message-ttl", 10000);
        // 设置队列消息长度
        // arguments.put("x-max-length", 10);
        // 设置队列绑定的死信交换机信息
        arguments.put("x-dead-letter-exchange", DLX_EXCHANGE_NAME);
        arguments.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        return QueueBuilder.durable("test-temp-queue").withArguments(arguments).build();
    }

    @Bean
    public Binding bindingTemp(@Qualifier("bootTempQueue") Queue queue, @Qualifier("bootTempExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("direct-temp").noargs();
    }
}
