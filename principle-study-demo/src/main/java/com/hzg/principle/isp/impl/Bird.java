package com.hzg.principle.isp.impl;

import com.hzg.principle.isp.IAnimal;

/**
 * @Package: com.hzg.principle.isp.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 16:46
 */
public class Bird implements IAnimal {

    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }

    @Override
    public void swim() {
        System.out.println("================鸟是不会游泳的================");
    }

}
