package com.hzg.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign客户端日志监控<br/>
 *
 * @Author HuangZhiGao
 * @Date 2020-12-21 10:26
 */
@Configuration
public class FeignConfig {

    /**
     * feign客户端日志级别设置<br/>
     *
     * @return feign.Logger.Level
     * @author HuangZhiGao
     * @date 2020-12-21 10:29:20
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
