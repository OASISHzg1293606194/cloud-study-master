package com.hzg.principle.ocp.impl;

/**
 * @Package: com.hzg.principle.ocp.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 14:57
 */
public class JavaDiscountCourse extends JavaCourse {

    private static final Double DISCOUNT = new Double(0.62);

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginalPrice() {
        return super.getPrice();
    }

    public Double getDiscountPrice() {
        return super.getPrice() * DISCOUNT;
    }

}
