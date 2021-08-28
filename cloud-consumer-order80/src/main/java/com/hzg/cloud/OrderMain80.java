package com.hzg.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 用户下单模块<br/>
 *
 * @author HaungZhiGao
 * @create  2020-07-28 21:57
 */
@SpringBootApplication
@EnableEurekaClient
// @RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = DefineRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
