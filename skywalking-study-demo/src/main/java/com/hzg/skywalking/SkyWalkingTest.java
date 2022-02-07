package com.hzg.skywalking;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.hzg.skywalking
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-12-03 15:16
 */
public class SkyWalkingTest {

    public static void main(String[] args) {
        System.out.println("This is a SkyWalking Test Main method.");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
