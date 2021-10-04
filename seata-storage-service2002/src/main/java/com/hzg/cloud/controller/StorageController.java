package com.hzg.cloud.controller;

import com.hzg.cloud.domain.CommonResult;
import com.hzg.cloud.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.hzg.cloud.controller
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 22:59
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/decrease")
    public CommonResult decreaseStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        storageService.decreaseStorage(productId, count);
        return new CommonResult(200, "扣减库存成功！");
    }

}
