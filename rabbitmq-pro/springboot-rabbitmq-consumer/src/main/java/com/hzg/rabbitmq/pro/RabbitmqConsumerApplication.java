package com.hzg.rabbitmq.pro;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package: com.hzg.rabbitmq.pro
 * @Description: rabbitmq服务消费者主启动类
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-29 15:40
 */
@SpringBootApplication
@EnableRabbit
public class RabbitmqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqConsumerApplication.class, args);
    }
}
