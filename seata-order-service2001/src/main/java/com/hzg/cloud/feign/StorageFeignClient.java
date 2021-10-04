package com.hzg.cloud.feign;

import com.hzg.cloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Package: com.hzg.cloud.feign
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-02 21:50
 */
@Component
@FeignClient(value = "seata-storage-service")
public interface StorageFeignClient {

    @PostMapping("/storage/decrease")
    CommonResult decreaseStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}
