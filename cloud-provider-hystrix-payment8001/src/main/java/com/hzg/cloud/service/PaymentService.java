package com.hzg.cloud.service;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-21 14:07
 */
public interface PaymentService {

    String getInfoSuccess();

    String getInfoFailed();

    String paymentCircuitBreaker(Integer id);

}
