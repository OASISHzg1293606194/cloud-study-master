package com.hzg.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Package: com.hzg.cloud
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 19:37
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class SeataStorageServiceApp2002 {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageServiceApp2002.class, args);
    }
}
