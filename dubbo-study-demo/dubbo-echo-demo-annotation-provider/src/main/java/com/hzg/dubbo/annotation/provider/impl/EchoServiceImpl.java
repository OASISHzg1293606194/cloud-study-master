package com.hzg.dubbo.annotation.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.hzg.dubbo.api.EchoService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Package: com.hzg.dubbo.annotation.provider.impl
 * @Description: 服务提供者[annotation版本]
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-29 21:00
 */
@Service
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        String nowTime = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
        System.out.println("[" + nowTime + "] Hello" + message + ", request from consume: " + RpcContext.getContext().getRemoteAddress());
        return message;
    }

}
