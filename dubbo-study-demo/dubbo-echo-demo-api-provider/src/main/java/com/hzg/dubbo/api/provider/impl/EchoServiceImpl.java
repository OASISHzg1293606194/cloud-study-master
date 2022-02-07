package com.hzg.dubbo.api.provider.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.hzg.dubbo.api.EchoService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Package: com.hzg.dubbo.api.provider.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-29 22:05
 */
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        String nowTime = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
        System.out.println("[" + nowTime + "] Hello" + message + ", request from consume: " + RpcContext.getContext().getRemoteAddress());
        return message;
    }

}
