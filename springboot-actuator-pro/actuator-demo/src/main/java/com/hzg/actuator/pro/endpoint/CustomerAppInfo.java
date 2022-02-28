package com.hzg.actuator.pro.endpoint;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.hzg.actuator.pro.endpoint
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-15 17:23
 */
@Component
public class CustomerAppInfo implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        // TODO 查询数据库数据配置信息
        Map<String, Object> detailsMap = new HashMap<>();
        detailsMap.put("oasis", "100%");
        detailsMap.put("appName", "springboot-admin-client");
        detailsMap.put("appVersion", "V2.0.6");
        builder.withDetails(detailsMap);
    }
}
