package com.hzg.dubbo.pro.service;

import com.hzg.dubbo.pro.pojo.User;

/**
 * @Package: com.hzg.dubbo.pro.service
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-12-27 13:04
 */
public interface UserService {

    String sayHello();

    User findUserByBizId(Integer bizId);

}
