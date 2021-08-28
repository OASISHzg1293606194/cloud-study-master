package com.hzg.cloud.feignservice;

import org.springframework.stereotype.Component;

/**
 * @Author HuangZhiGao
 * @Date 2020-12-21 17:40
 */
@Component
public class PaymentServiceClientHystrixHandler implements PaymentServiceClient {

    @Override
    public String getInfoSuccess() {
        return "PaymentServiceClientHystrixHandler getInfoSuccess fallback.";
    }

    @Override
    public String getInfoFailed() {
        return "PaymentServiceClientHystrixHandler getInfoFailed fallback.";
    }

}
