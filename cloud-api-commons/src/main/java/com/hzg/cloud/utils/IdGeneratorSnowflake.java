package com.hzg.cloud.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package: com.hzg.cloud.utils
 * @Description: Hutool雪花算法生成id
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-04 22:52
 */
// @Component
// @Slf4j
public class IdGeneratorSnowflake {

    private long workerId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    @PostConstruct
    public void init() {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
            e.printStackTrace();
        }
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        // System.out.println(new IdGeneratorSnowflake().snowflakeId());

        IdGeneratorSnowflake snowflake = new IdGeneratorSnowflake();
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 2; i++) {
            threadPool.submit(() -> {
                System.out.println(snowflake.snowflakeId());
            });
        }
        threadPool.shutdown();
    }

}
