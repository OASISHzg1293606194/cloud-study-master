package com.hzg.cloud.service;


import java.math.BigDecimal;

/**
 * @Package: com.hzg.cloud.service
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 23:23
 */
public interface AccountService {

    void decreaseAccount(Long userId, BigDecimal money);

}
