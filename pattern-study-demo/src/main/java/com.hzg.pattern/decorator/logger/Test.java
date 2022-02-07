package com.hzg.pattern.decorator.logger;

import org.slf4j.Logger;

import java.io.PrintStream;

/**
 * @Package: com.hzg.pattern.decorator.logger
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 22:02
 */
public class Test {

    private static final Logger LOGGER = JsonLoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        LOGGER.info("提示信息");
        LOGGER.error("系统错误");

        PrintStream printStreamOut = System.out;
        PrintStream printStreamErr = System.err;
        printStreamOut.println("提示信息");
        printStreamErr.println("系统错误");
    }

}
