package com.hzg.cloud.feignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-21 15:19
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = PaymentServiceClientHystrixHandler.class)
public interface PaymentServiceClient {

    @GetMapping("/payment/hystrix/getInfoSuccess")
    String getInfoSuccess();

    @GetMapping("/payment/hystrix/timeout/getInfoFailed")
    String getInfoFailed();

}
