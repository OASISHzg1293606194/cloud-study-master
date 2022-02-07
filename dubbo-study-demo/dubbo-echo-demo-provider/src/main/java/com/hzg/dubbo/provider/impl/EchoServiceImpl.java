package com.hzg.dubbo.provider.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.hzg.dubbo.api.EchoService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Package: com.hzg.dubbo.provider.impl
 * @Description: 服务提供者
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-22 23:43
 */
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        String nowTime = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
        System.out.println("[" + nowTime + "] Hello" + message + ", request from consume: " + RpcContext.getContext().getRemoteAddress());
        return message;
    }

}
