package com.hzg.principle.ocp;

import com.hzg.principle.ocp.impl.JavaCourse;
import com.hzg.principle.ocp.impl.JavaDiscountCourse;

/**
 * @Package: com.hzg.principle.ocp
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 15:04
 */
public class Test {
    public static void main(String[] args) {
        ICourse javaCourse = new JavaCourse(1000, "Java架构课程", new Double(12000.88));
        System.out.println(javaCourse.getId());
        System.out.println(javaCourse.getName());
        System.out.println(javaCourse.getPrice());

        System.out.println("================优惠后================");
        JavaDiscountCourse javaDiscountCourse = new JavaDiscountCourse(1000, "Java架构课程", new Double(12000.88));
        System.out.println(javaDiscountCourse.getId());
        System.out.println(javaDiscountCourse.getName());
        System.out.println(javaDiscountCourse.getDiscountPrice());
    }
}
