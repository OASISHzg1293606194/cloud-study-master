package com.hzg.actuator.pro.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.hzg.actuator.pro.endpoint
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-15 18:36
 */
@Component
@Endpoint(id = "myServiceEndpoint")
public class MyServiceEndpoint {

    public Map<String, Object> infoMap = new HashMap<>();


    public MyServiceEndpoint() {
        infoMap.put("name", "oasis");
        infoMap.put("age", 19);
    }

    @ReadOperation
    public Map getInfo() {
        // 端点的读操作
        return infoMap;
    }

    @WriteOperation
    public void putInfo() {
        // 端点的写操作
        infoMap.put("skill", "100%");
    }

    @DeleteOperation
    public void doDeleteSomething() {
        // 端点的删除操作
        infoMap.clear();
    }

}
