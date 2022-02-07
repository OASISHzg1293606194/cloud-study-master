package com.hzg.dubbo.api.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.hzg.dubbo.api.EchoService;
import com.hzg.dubbo.api.provider.impl.EchoServiceImpl;


/**
 * @Package: com.hzg.dubbo.api.provider
 * @Description: 服务提供者[api版本]
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-29 21:58
 */
public class ApiEchoProvider {

    public static void main(String[] args) throws Exception {
        ServiceConfig<EchoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(new ApplicationConfig("java-api-echo-provider"));
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        serviceConfig.setInterface(EchoService.class);
        serviceConfig.setRef(new EchoServiceImpl());
        serviceConfig.export();
        System.out.println("java-api-echo-provider is running.");
        System.in.read();
    }

}
