package com.hzg.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-12 11:47
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class OrderController {

    /**
     * consul版的支付服务，需要取consul中的服务名称
     */
    public static final String PAYMENT_URL = "http://cloud-paymentconsul-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String paymentConsul() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
    }

}
