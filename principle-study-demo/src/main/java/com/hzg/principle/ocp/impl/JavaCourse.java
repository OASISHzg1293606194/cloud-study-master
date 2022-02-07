package com.hzg.principle.ocp.impl;

import com.hzg.principle.ocp.ICourse;

/**
 * @Package: com.hzg.principle.ocp.impl
 * @Description: Java课程具体实现类
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 14:45
 * 如果说Java课程要做优惠，修改的前提是不能影响原始功能-即不能修改原始功能的代码<br/>
 * 这个时候就要扩展一个JavaDiscountCourse类来实现价格优惠了
 */
public class JavaCourse implements ICourse {

    private Integer id;
    private String name;
    private Double price;


    public JavaCourse(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

}
