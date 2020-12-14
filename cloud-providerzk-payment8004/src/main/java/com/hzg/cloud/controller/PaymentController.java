package com.hzg.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-11 16:25
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/zk")
    public String paymentZookeeper() {
        String msg = "springcloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
        return msg;
    }

}
