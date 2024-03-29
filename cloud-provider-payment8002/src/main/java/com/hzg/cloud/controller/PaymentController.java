package com.hzg.cloud.controller;

import com.hzg.cloud.entities.CommonResult;
import com.hzg.cloud.entities.Payment;
import com.hzg.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HaungZhiGao
 * @create  2020-07-27 18:02
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("======>创建payment结果：serverPort：" + serverPort + result);

        if (result > 0) {
            return new CommonResult(0, "操作成功", result);
        }
        return new CommonResult(1, "操作失败", null);
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("======>查询payment结果：serverPort：" + serverPort + payment);

        return new CommonResult(0, "查询成功", payment);
    }

    @GetMapping("/getDiscoveryInfo")
    public Object getDiscoveryInfo() {
        List<String> services = discoveryClient.getServices();
        for (String elem : services) {
            log.info("PaymentMain8002-service-{}", elem);
        }

        // 根据注册进eureka中的服务名称获取服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance elem : instances) {
            log.info("PaymentMain8002-instance-{}", elem.getServiceId() + "\t" + elem.getHost() + ":" + elem.getPort() + "\t" + elem.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/getLoadBalancer")
    public String getLoadBalancer() {
        return serverPort;
    }

}
