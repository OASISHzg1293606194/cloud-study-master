package com.hzg.cloud.feign;

import com.hzg.cloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Package: com.hzg.cloud.feign
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-02 21:50
 */
@Component
@FeignClient(value = "seata-account-service")
public interface AccountFeignClient {

    @PostMapping("/account/decrease")
    CommonResult decreaseAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

}
