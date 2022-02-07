package com.hzg.pattern.decorator.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package: com.hzg.pattern.decorator.logger
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 22:24
 */
public class JsonLoggerFactory {

    public static JsonLogger getLogger(Class clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        return new JsonLogger(logger);
    }

}
