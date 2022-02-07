package com.hzg.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Package: com.hzg.dubbo.provider
 * @Description: 服务提供者主启动类
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-23 0:07
 */
public class EchoProvider {

    public static void main(String[] args) throws Exception {
        // 指定服务暴露配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/echo-demo-provider.xml"});
        // 启动Spring容器
        context.start();

        System.in.read();
    }

}
