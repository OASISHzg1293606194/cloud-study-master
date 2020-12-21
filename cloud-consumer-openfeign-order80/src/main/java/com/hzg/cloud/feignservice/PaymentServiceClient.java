package com.hzg.cloud.feignservice;

import com.hzg.cloud.entities.CommonResult;
import com.hzg.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 使用OpenFeign封装payment服务调用的客户端<be/>
 *
 * @Author HuangZhiGao
 * @Date 2020-12-19 17:38
 */
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentServiceClient {

    @PostMapping("/payment/create")
    CommonResult create(@RequestBody Payment payment);

    @GetMapping("/payment/getPaymentById/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/getDiscoveryInfo")
    Object getDiscoveryInfo();

    @GetMapping("/payment/getLoadBalancer")
    String getLoadBalancer();

    @GetMapping("/payment/paymentFeignTimeout")
    String paymentFeignTimeout();

}
