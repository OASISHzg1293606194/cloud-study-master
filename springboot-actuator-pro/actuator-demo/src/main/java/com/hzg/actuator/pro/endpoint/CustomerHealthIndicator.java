package com.hzg.actuator.pro.endpoint;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @Package: com.hzg.actuator.pro.endpoint
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-15 16:29
 */
@Component
public class CustomerHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try {
            boolean health = checkHealth();
            if (health) {
                builder.up().withDetail("total", "1");
            } else {
                builder.down();
            }
        } catch (Exception e) {
            builder.down().withException(e);
        }
    }

    private boolean checkHealth() {
        // TODO 检验健康状态
        return true;
    }

}
