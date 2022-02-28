package com.hzg.actuator.pro.controller;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.hzg.actuator.pro.controller
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-13 17:52
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam("username") String username) {
        // try {
        //     TimeUnit.SECONDS.sleep(5);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // 定制基于Metrics的计数器
        Metrics.counter("oasis.controller.sayHello.requestCounter", "username", username).increment();

        // 计时器
        Timer timer = Metrics.timer("oasis.controller.sayHello.timer", "username", username);
        timer.record(() -> {
            if (!"oasis".equals(username)) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 仪表
        Metrics.gauge("oasis.controller.sayHello.gauge", 1);

        // 摘要
        DistributionSummary summary = Metrics.summary("oasis.controller.sayHello.summary");
        summary.record(1);

        System.out.println("hello successful");
        return "hello successful";
    }

}
