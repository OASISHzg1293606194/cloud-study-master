package com.hzg.cloud.service.impl;

import com.hzg.cloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Package: com.hzg.cloud.service.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-09-02 22:45
 */
@Slf4j
/** 定义消息的推送管道 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel output;


    @Override
    public String sendMsg() {
        String number = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(number).build());
        log.info("流水号==========>[{}]", number);
        return null;
    }

}
