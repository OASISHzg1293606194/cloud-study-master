package com.hzg.cloud.controller;

import com.hzg.cloud.feignservice.PaymentServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-21 15:18
 */
@Slf4j
@RestController
@RequestMapping("/customer")
@DefaultProperties(defaultFallback = "getInfoFailedGlobalHandler")
public class OrderFeignHystrixController {

    @Resource
    private PaymentServiceClient paymentServiceClient;

    @GetMapping("/feign/hystrix/getInfoSuccess")
    public String getInfoSuccess() {
        return paymentServiceClient.getInfoSuccess();
    }

    // @HystrixCommand(fallbackMethod = "getInfoFailedHandler", commandProperties = {
    //         @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "2000")
    // })
    @HystrixCommand
    @GetMapping("/feign/hystrix/timeout/getInfoFailed")
    public String getInfoFailed() {
        return paymentServiceClient.getInfoFailed();
    }

    public String getInfoFailedHandler() {
        return "80服务反馈：系统繁忙，请10s后再试！ -- " + Thread.currentThread().getName();
    }

    /** 
     * 全局服务降级
     *
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020-12-21 17:27:38
     */
    public String getInfoFailedGlobalHandler() {
        return "Is Global.【服务反馈：系统繁忙，请10s后再试！】 -- " + Thread.currentThread().getName();
    }

}
