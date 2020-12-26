package com.hzg.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hzg.cloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-21 14:09
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String getInfoSuccess() {
        return "200 OK -- " + Thread.currentThread().getName();
    }

    /**
     * 服务降级fallback<br/>
     *
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020-12-22 08:55:39
     */
    @HystrixCommand(fallbackMethod = "getInfoFailedHandler", commandProperties = {
            // 设置服务自身调用超时时间的峰值 3s
            // HystrixProperty的name可以从HystrixPropertiesManager中找
            // HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @Override
    public String getInfoFailed() {
        // 运行错误
        // int age = 10 / 0;

        // 线程休眠5s
        int sleepTime = 6;
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "500 ERROR -- " + Thread.currentThread().getName();
    }

    public String getInfoFailedHandler() {
        return "系统繁忙，请稍后再试！ -- " + Thread.currentThread().getName();
    }


    /**
     * 服务熔断<br/>
     *
     * @return
     * @author HuangZhiGao
     * @date 2020-12-22 08:56:42
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            // 是否开启断路器
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
            // 请求次数
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "10"),
            // 时间窗口期/时间范围
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "10000"),
            // 失败率达到多少后跳闸
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "60")

            // 10s内请求10次有60%失败就会跳闸
            // 意味着达到跳闸的阈值就会开启断路器，接口失败率达到阈值之后即使正确的访问也会返回错误的提示，过一段时间会根据正确率的提升而关闭断路器并返回正确的结果
    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("=========>id不能为负数");
        }

        String simpleUUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " --\t调用成功，流水号：" + simpleUUID;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "id不能为负数【" + id + "】，请确认后输入";
    }

}
