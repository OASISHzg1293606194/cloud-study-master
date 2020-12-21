package com.hzg.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author HaungZhiGao
 * @create  2020-07-28 22:52
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced // 不使用默认的负载均衡策略
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
