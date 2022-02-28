package com.hzg.jmx;

/**
 * @Package: com.hzg.jmx
 * @Description: 【注意】MBean接口，接口名必须以MBean结尾
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-11 13:23
 */
public interface HelloMBean {

    String getBizId();

    void setBizId(String bizId);

    String getName();

    void setName(String name);


    void sayHelloWorld();

}
