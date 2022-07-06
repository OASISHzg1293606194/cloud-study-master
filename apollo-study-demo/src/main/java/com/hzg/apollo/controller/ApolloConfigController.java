package com.hzg.apollo.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.hzg.apollo.controller
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-01 14:29
 */
@RestController
@RequestMapping("/apollo")
public class ApolloConfigController {

    @ApolloConfig
    private Config config;


    @GetMapping("/getConfig")
    public String getConfig() {
        return "im.enable:" + config.getProperty("im.enable", null);
    }

}
