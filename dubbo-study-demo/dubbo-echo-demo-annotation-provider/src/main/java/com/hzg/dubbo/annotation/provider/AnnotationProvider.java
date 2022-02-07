package com.hzg.dubbo.annotation.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.hzg.dubbo.annotation.provider.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-29 21:04
 */
public class AnnotationProvider {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.in.read();
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.hzg.dubbo.annotation.provider")
    static class ProviderConfiguration {
        @Bean
        public ProviderConfig providerConfig() {
            return new ProviderConfig();
        }

        @Bean
        public ApplicationConfig applicationConfig() {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            applicationConfig.setName("echo-annotation-provider");
            return applicationConfig;
        }

        @Bean
        public RegistryConfig registryConfig() {
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setProtocol("zookeeper");
            registryConfig.setAddress("localhost");
            registryConfig.setPort(2181);
            return registryConfig;
        }

        @Bean
        public ProtocolConfig protocolConfig() {
            ProtocolConfig protocolConfig = new ProtocolConfig();
            protocolConfig.setName("dubbo");
            protocolConfig.setPort(20880);
            return protocolConfig;
        }
    }

}
