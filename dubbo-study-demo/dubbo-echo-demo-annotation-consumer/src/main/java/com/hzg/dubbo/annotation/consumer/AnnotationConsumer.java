package com.hzg.dubbo.annotation.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.hzg.dubbo.annotation.consumer
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-29 21:24
 */
public class AnnotationConsumer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();

        EchoConsumer echoConsumer = context.getBean(EchoConsumer.class);
        String echo = echoConsumer.echo("Hello world!");
        System.out.println("result:" + echo);
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.hzg.dubbo.annotation.consumer")
    @ComponentScan(value = {"com.hzg.dubbo.annotation.consumer"})
    static class ConsumerConfiguration {
        @Bean
        public ConsumerConfig consumerConfig() {
            return new ConsumerConfig();
        }

        @Bean
        public ApplicationConfig applicationConfig() {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            applicationConfig.setName("echo-annotation-consumer");
            return applicationConfig;
        }

        @Bean
        public RegistryConfig registryConfig() {
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setProtocol("zookeeper");
            registryConfig.setAddress("localhost");
            registryConfig.setPort(2181);
            return registryConfig;
        }
    }

}
