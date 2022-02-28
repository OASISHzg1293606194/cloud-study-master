package com.hzg.actuator.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * @Package: com.hzg.actuator.pro
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-10 23:40
 */
@SpringBootApplication
public class ActuatorApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ActuatorApplication.class);
        // 将进程号写入一个app.pid文件
        application.addListeners(new ApplicationPidFileWriter("./springboot-actuator-pro/app/app.pid"));
        application.run();
    }
}
