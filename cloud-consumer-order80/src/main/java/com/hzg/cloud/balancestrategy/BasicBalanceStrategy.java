package com.hzg.cloud.balancestrategy;

import org.springframework.cloud.client.ServiceInstance;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 自定义负载均衡策略顶层接口<br/>
 *
 * @Author HuangZhiGao
 * @Date 2020-12-14 15:41
 */
public interface BasicBalanceStrategy {

    ServiceInstance serverInstance(@NotNull List<ServiceInstance> instances);

}
