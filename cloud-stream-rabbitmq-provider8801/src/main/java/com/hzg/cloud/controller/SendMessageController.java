package com.hzg.cloud.controller;

import com.hzg.cloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.hzg.cloud.controller
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-09-02 22:58
 */
@RestController
@RequestMapping("/message")
public class SendMessageController {

    @Resource
    private IMessageProvider iMessageProvider;

    @PostMapping("/sendMsg")
    public String sendMsg() {
        return iMessageProvider.sendMsg();
    }

}
