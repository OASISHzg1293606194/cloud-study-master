package com.hzg.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package: com.hzg.apollo
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-01 13:59
 */
@EnableApolloConfig
@SpringBootApplication
public class StudyApolloApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApolloApplication.class, args);
    }
}
