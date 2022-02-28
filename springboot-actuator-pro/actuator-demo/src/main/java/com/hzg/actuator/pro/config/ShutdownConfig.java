package com.hzg.actuator.pro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.hzg.actuator.pro.config
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-14 16:51
 */
@Configuration
public class ShutdownConfig {

    @Bean
    public TerminateBean terminateBean() {
        return new TerminateBean();
    }

}
