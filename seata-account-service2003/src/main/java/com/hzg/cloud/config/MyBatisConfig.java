package com.hzg.cloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.hzg.cloud.dao"})
public class MyBatisConfig {
}
