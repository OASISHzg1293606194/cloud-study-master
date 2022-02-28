package com.hzg.dubbo.pro.controller;

import com.hzg.dubbo.pro.pojo.User;
import com.hzg.dubbo.pro.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.hzg.dubbo.pro.controller
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-12-27 13:19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    // @Reference(timeout = 1000, retries = 0)
    // @Reference(version = "v2.0")
    // @Reference(loadbalance = "random")
    // @Reference(cluster = "failover")
    // @Reference(mock = "force:return null")
    @Reference(mock = "fail:return null")
    private UserService userService;


    @GetMapping("/sayHello")
    public String sayHello() {
        return userService.sayHello();
    }

    private int i = 1;

    @GetMapping("/findUser")
    public User findUser(@RequestParam("bizId") Integer bizId) {
        // new Thread(new Runnable() {
        //     public void run() {
        //         while (true) {
        //             System.out.println(i++);
        //             try {
        //                 TimeUnit.SECONDS.sleep(1);
        //             } catch (InterruptedException e) {
        //                 e.printStackTrace();
        //             }
        //         }
        //     }
        // }).start();
        return userService.findUserByBizId(bizId);
    }

}
