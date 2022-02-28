package com.hzg.actuator.pro.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.hzg.actuator.pro.controller
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-14 16:57
 */
@RestController
@RequestMapping("/serverShutdown")
public class ShutdownController implements ApplicationContextAware {

    private ApplicationContext context;


    @PostMapping("/doShutdown")
    public String doShutdown() {
        // TODO 通过调用接口关闭服务，同时添加参数并设置参数校验逻辑，避免错误调用
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) context;
        ctx.close();
        System.out.println("========================context is shutdown");
        return "context is shutdown";
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
