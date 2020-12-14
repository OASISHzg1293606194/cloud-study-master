package com.hzg.definerbnrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义ribbon负载均衡规则<br/>
 *
 * @Author HuangZhiGao
 * @Date 2020-12-12 17:53
 */
@Configuration
public class DefineRule {

    @Bean
    public IRule myRule() {
        // 定义为随机
        return new RandomRule();
    }

}
