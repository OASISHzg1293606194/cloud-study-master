package com.hzg.actuator.pro.config;

import javax.annotation.PreDestroy;

/**
 * @Package: com.hzg.actuator.pro.config
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-14 16:52
 */
public class TerminateBean {

    @PreDestroy
    public void preDestroy() {
        System.out.println("========================服务关闭前执行========================");
        System.out.println("========================TerminateBean is destroy");
        System.out.println("========================服务关闭前执行========================");
    }

}
