package com.hzg.dubbo.api.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.hzg.dubbo.api.EchoService;

/**
 * @Package: com.hzg.dubbo.api.consumer
 * @Description: 服务消费者[api版本]
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-29 22:00
 */
public class ApiEchoConsumer {

    public static void main(String[] args) {
        ReferenceConfig<EchoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("java-api-echo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(EchoService.class);
        EchoService echoService = referenceConfig.get();
        String echo = echoService.echo("Hello world!");
        System.out.println("result: " + echo);
    }

}
