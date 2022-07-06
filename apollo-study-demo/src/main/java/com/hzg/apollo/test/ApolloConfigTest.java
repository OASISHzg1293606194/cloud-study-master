package com.hzg.apollo.test;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.util.Set;

/**
 * @Package: com.hzg.apollo.test
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-02 11:05
 */
public class ApolloConfigTest {
    /**
     * 需要配置运行时系统参数
     * -Dapp.id=apollo-study-demo -Denv=DEV -Ddev_meta=http://localhost:8080/
     * -Dapp.id=oasis-service -Denv=DEV -Ddev_meta=http://localhost:8080/
     * -Dapp.id=oasis-service -Denv=DEV -Dapollo.cluster=CHANGSHA -Ddev_meta=http://localhost:8080/
     */
    public static void main(String[] args) {
        Config appConfig = ConfigService.getAppConfig();
        String property = appConfig.getProperty("im.enable", null);
        System.out.println("im.enable:" + property);

        Config config1 = ConfigService.getConfig("minio-config.yml");
        String property1 = config1.getProperty("minio.accessKey", null);
        System.out.println("minio.accessKey:" + property1);

        Config config2 = ConfigService.getConfig("rabbitmq-config");
        String property2 = config2.getProperty("spring.rabbitmq.host", null);
        System.out.println("spring.rabbitmq.host:" + property2);

        Config config3 = ConfigService.getConfig("minio-config.yml");
        Set<String> propertyNames = config3.getPropertyNames();
        for (String propertyKey : propertyNames) {
            System.out.println("====>" + propertyKey + ":" + config3.getProperty(propertyKey, null));
        }

        Config config4 = ConfigService.getConfig("oasis-dept.common-nacos-config");
        Set<String> propertyKeys = config4.getPropertyNames();
        for (String propertyKey : propertyKeys) {
            System.out.println("---->" + propertyKey + ":" + config4.getProperty(propertyKey, null));
        }
    }
}
