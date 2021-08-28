package com.hzg.cloud.service;

import com.hzg.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author HaungZhiGao
 * @create  2020-07-27 17:54
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
