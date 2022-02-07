package com.hzg.principle.dip;

import com.hzg.principle.dip.impl.AICourse;
import com.hzg.principle.dip.impl.JavaCourse;
import com.hzg.principle.dip.impl.PythonCourse;

/**
 * @Package: com.hzg.principle.dip
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 15:28
 */
public class Test {
    public static void main(String[] args) {
        Developer developer = new Developer();
        developer.doStudy(new JavaCourse());
        developer.doStudy(new PythonCourse());

        System.out.println("================依赖注入================");
        /** 依赖注入-构造器方式注入 */
        Developer developer1 = new Developer(new AICourse());
        developer1.doStudyFromDI();

        /** 依赖注入-Setter方式注入 */
        Developer developer2 = new Developer();
        developer2.setCourse(new JavaCourse());
        developer2.doStudyFromDI();
    }
}
