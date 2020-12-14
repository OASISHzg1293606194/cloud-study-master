package com.hzg.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-11 17:59
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class OrderController {

    /**
     * zookeeper版的支付服务，需要取zookeeper中的服务名称
     */
    public static final String PAYMENT_URL = "http://cloud-paymentzk-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/zk")
    public String paymentZookeeper() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
    }

}
