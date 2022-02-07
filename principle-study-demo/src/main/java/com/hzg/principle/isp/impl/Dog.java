package com.hzg.principle.isp.impl;

import com.hzg.principle.isp.IAnimal;

/**
 * @Package: com.hzg.principle.isp.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 16:45
 */
public class Dog implements IAnimal {

    @Override
    public void eat() {
    }

    @Override
    public void fly() {
        System.out.println("================狗是不会飞的================");
    }

    @Override
    public void swim() {
    }

}
