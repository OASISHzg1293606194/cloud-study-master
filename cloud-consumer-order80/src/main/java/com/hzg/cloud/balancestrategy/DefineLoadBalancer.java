package com.hzg.cloud.balancestrategy;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义负载均衡策略实现类<br/>
 * 注意@Component注解<br/>
 *
 * @Author HuangZhiGao
 * @Date 2020-12-14 15:44
 */
@Component
public class DefineLoadBalancer implements BasicBalanceStrategy {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance serverInstance(@NotNull List<ServiceInstance> instances) {
        if (!CollectionUtils.isEmpty(instances)) {
            int index = getAndIncrement() % instances.size();
            return instances.get(index);
        }
        return null;
    }

    public final int getAndIncrement() {
        int currentIdx;
        int nextIdx;

        do {
            currentIdx = this.atomicInteger.get();
            nextIdx = currentIdx >= Integer.MAX_VALUE ? 0 : currentIdx + 1;
            // Integer.MAX_VALUE => 2147483647
        } while (!this.atomicInteger.compareAndSet(currentIdx, nextIdx));
        System.out.println("****** " + nextIdx + " ******");

        return nextIdx;
    }

}
