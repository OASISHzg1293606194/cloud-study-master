package com.hzg.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.hzg.cloud.com.hzg.cloud.controller
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-09-05 13:53
 */
@RestController
@RequestMapping("/payment/nacos")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/getPaymentById/{id}")
    public String getPaymentFromNacos(@PathVariable("id") Long id) {
        return "From nacos server " + serverPort + " id is " + id;
    }

}
