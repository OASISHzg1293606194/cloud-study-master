package com.hzg.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-19 17:25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderOpenFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignMain80.class, args);
    }
}
