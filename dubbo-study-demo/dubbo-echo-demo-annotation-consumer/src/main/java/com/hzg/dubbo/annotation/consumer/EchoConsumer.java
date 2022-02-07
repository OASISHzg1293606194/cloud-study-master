package com.hzg.dubbo.annotation.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hzg.dubbo.api.EchoService;
import org.springframework.stereotype.Component;

/**
 * @Package: com.hzg.dubbo.annotation.consumer
 * @Description: 服务消费者[annotation版本]
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-29 21:34
 */
@Component
public class EchoConsumer {

    @Reference
    private EchoService echoService;

    public String echo(String message) {
        return echoService.echo(message);
    }

}
