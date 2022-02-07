package com.hzg.rabbitmq.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package: com.hzg.rabbitmq.pro
 * @Description: rabbitmq服务提供者主启动类
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-29 13:24
 */
@SpringBootApplication
public class RabbitmqProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProviderApplication.class, args);
    }
}
