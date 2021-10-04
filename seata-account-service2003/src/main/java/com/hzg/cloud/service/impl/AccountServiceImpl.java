package com.hzg.cloud.service.impl;

import com.hzg.cloud.dao.AccountDao;
import com.hzg.cloud.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.hzg.cloud.service.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 23:24
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    public void decreaseAccount(Long userId, BigDecimal money) {
        LOGGER.info("===================>扣减账户余额-begin");
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decreaseAccount(userId, money);
        LOGGER.info("===================>扣减账户余额-end");
    }

}
