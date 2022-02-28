package com.hzg.cloud.controller;

import com.hzg.cloud.entities.CommonResult;
import com.hzg.cloud.entities.Payment;
import com.hzg.cloud.feignservice.PaymentServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-19 17:52
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class OrderOpenFeignController {

    @Resource
    private PaymentServiceClient paymentServiceClient;

    @PostMapping("/openFeign/create")
    public CommonResult create(@RequestBody Payment payment) {
        return paymentServiceClient.create(payment);
    }

    @GetMapping("/openFeign/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {

        return paymentServiceClient.getPaymentById(id);
    }

    @GetMapping("/openFeign/getDiscoveryInfo")
    public Object getDiscoveryInfo() {
        return paymentServiceClient.getDiscoveryInfo();
    }

    @GetMapping("/openFeign/getLoadBalancer")
    public String getLoadBalancer() {
        return paymentServiceClient.getLoadBalancer();
    }

    /**
     * 用于演示openFeign调用超时<br/>
     * openfeign-ribbon 客户端调用一般等待1s钟<br/>
     *
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020-12-21 09:48:41
     */
    @GetMapping("/openFeign/paymentFeignTimeout")
    public String paymentFeignTimeout() {
        return paymentServiceClient.paymentFeignTimeout();
    }

}
