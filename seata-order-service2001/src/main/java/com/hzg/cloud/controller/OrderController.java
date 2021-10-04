package com.hzg.cloud.controller;

import com.hzg.cloud.domain.CommonResult;
import com.hzg.cloud.domain.Order;
import com.hzg.cloud.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.hzg.cloud.controller
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-03 18:51
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public CommonResult createOrder(@RequestBody Order order) {
        orderService.create(order);
        return new CommonResult(200, "下单成功！");
    }

}
