package com.hzg.crawler.demo;


import org.apache.log4j.Logger;

/**
 * @Package: com.hzg.crawler.demo
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-15 12:55
 */
public class Log4jTest {

    private static final Logger LOGGER = Logger.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        System.out.println("hello");
        // 日志信息
        LOGGER.info("hello world");
        LOGGER.debug("This is debug message.");
        LOGGER.error("This is error message.");
        LOGGER.warn("This is warn message.");
    }

}
