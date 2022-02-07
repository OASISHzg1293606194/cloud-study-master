package com.hzg.cloud.controller;

import com.hzg.cloud.balancestrategy.BasicBalanceStrategy;
import com.hzg.cloud.entities.CommonResult;
import com.hzg.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author HaungZhiGao
 * @create  2020-07-28 22:26
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class OrderController {

    /**
     * 单机版的支付服务
     */
    // public static final String PAYMENT_URL = "http://localhost:8001";

    /**
     * 集群版的支付服务，需要取Eureka中的集群服务名称
     */
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";


    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private BasicBalanceStrategy basicBalanceStrategy;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/insert")
    public CommonResult<Payment> insert(Payment payment) {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        log.info("======>" + entity.getStatusCode() + "\t" + entity.getHeaders());
        return entity.getBody();
    }

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
    }

    @GetMapping("/payment/selPaymentById/{id}")
    public CommonResult<Payment> selPaymentById(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
        log.info("======>" + entity.getStatusCode() + "\t" + entity.getHeaders());
        return entity.getBody();
    }

    @GetMapping("/payment/getLoadBalancer")
    public String getLoadBalancer() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (!CollectionUtils.isEmpty(instances)) {
            ServiceInstance serviceInstance = basicBalanceStrategy.serverInstance(instances);
            URI uri = serviceInstance.getUri();
            return restTemplate.getForObject(uri + "/payment/getLoadBalancer", String.class);
        }
        return null;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin", String.class);
    }

}
