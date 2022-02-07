package com.hzg.pattern.decorator.logger;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;

/**
 * @Package: com.hzg.pattern.decorator.logger
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 22:09
 */
public class JsonLogger extends LoggerDecorator {

    public JsonLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void info(String msg) {
        JSONObject jsonObject = getJsonObject();
        jsonObject.putIfAbsent("message", msg);
        logger.info(jsonObject.toString());
    }

    @Override
    public void error(String msg) {
        JSONObject jsonObject = getJsonObject();
        jsonObject.putIfAbsent("message", msg);
        logger.error(jsonObject.toString());
    }

    private JSONObject getJsonObject() {
        return new JSONObject();
    }

}
