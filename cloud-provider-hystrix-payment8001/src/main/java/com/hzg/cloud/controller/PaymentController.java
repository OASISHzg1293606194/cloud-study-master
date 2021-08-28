package com.hzg.cloud.controller;

import com.hzg.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-21 14:10
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/getInfoSuccess")
    public String getInfoSuccess() {
        String info = paymentService.getInfoSuccess();
        log.info("===============>" + info);
        return info;
    }

    @GetMapping("/hystrix/timeout/getInfoFailed")
    public String getInfoFailed() {
        String info = paymentService.getInfoFailed();
        log.info("===============>" + info);
        return info;
    }

    @GetMapping("/hystrix/paymentCircuitBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String info = paymentService.paymentCircuitBreaker(id);
        log.info("===============>" + info);
        return info;
    }

}
