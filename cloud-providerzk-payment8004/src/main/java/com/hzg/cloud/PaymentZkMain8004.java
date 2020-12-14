package com.hzg.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 支付服务8004，注册到zookeeper<br/>
 *
 * @Author HuangZhiGao
 * @Date 2020-12-11 16:11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentZkMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentZkMain8004.class, args);
    }
}
