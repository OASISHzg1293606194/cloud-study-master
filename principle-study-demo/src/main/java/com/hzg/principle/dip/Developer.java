package com.hzg.principle.dip;

/**
 * @Package: com.hzg.principle.dip
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 15:19
 */
public class Developer {

    /**
     * 课程学习行为的方法
     * <p/>
     *
     * @param course
     * @return void
     * @author HuangZhiGao
     * @date 2021/11/5/005 15:20
     */
    public void doStudy(ICourse course) {
        course.study();
    }


    private ICourse course;

    public Developer() {
    }

    /**
     * 依赖注入-构造器方式注入
     */
    public Developer(ICourse course) {
        this.course = course;
    }

    /**
     * 依赖注入-Setter方式注入
     */
    public void setCourse(ICourse course) {
        this.course = course;
    }

    public void doStudyFromDI() {
        this.course.study();
    }

}
