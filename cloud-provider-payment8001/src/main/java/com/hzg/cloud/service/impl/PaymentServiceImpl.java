package com.hzg.cloud.service.impl;

import com.hzg.cloud.dao.PaymentDao;
import com.hzg.cloud.entities.Payment;
import com.hzg.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author HaungZhiGao
 * @create  2020-07-27 17:56
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * @Autowired
     */
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
