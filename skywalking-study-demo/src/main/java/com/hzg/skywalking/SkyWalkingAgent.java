package com.hzg.skywalking;

import java.lang.instrument.Instrumentation;

/**
 * @Package: com.hzg.skywalking
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-12-03 14:55
 */
public class SkyWalkingAgent {

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Hello SkyWalking! This is a JavaAgent demo.");
        instrumentation.addTransformer(new SkyWalkingTransformer());
    }

}
