package com.hzg.dubbo.pro.service.impl;

import com.hzg.dubbo.pro.pojo.User;
import com.hzg.dubbo.pro.service.UserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.hzg.dubbo.pro.service.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-12-27 13:05
 *
 * <p>@Service</p>
 * <p>timeout 超时时间(ms)</p>
 * <p>retries 重试次数</p>
 */
// @Service(timeout = 5000, retries = 3, version = "v1.0")
// @Service(weight = 300)
@Service
public class UserServiceImpl implements UserService {

    public String sayHello() {
        System.out.println("=============================");
        return "hello dubbo hello";
    }

    private int i = 1;

    public User findUserByBizId(Integer bizId) {
        System.out.println("old service impl=============================" + bizId);
        // System.out.println(i++);
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new User(bizId, "OASIS", "******");
    }

}
