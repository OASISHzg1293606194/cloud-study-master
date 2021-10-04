package com.hzg.cloud.controller;

import com.hzg.cloud.domain.CommonResult;
import com.hzg.cloud.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Package: com.hzg.cloud.controller
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 23:26
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/decrease")
    public CommonResult decreaseAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decreaseAccount(userId, money);
        return new CommonResult(200, "扣减余额成功！");
    }

}
