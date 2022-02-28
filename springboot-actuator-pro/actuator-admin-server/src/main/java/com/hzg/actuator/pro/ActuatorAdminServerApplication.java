package com.hzg.actuator.pro;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package: com.hzg.actuator.pro
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-14 17:51
 */
@SpringBootApplication
@EnableAdminServer
public class ActuatorAdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActuatorAdminServerApplication.class, args);
    }
}
