package com.hzg.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.hzg.cloud
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-08-29 18:40
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/configClient")
public class ConfigClientController {

    @Value("${server.port}")
    private String port;

    @Value("${config.info}")
    private String info;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {
        return "serverPort: " + port + "\t\n\n configInfo: " + info;
    }

}
