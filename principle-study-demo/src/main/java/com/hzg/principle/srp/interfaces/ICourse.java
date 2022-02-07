package com.hzg.principle.srp.interfaces;

/**
 * @Package: com.hzg.principle.srp.interfaces
 * @Description: 课程学习顶层接口
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 16:25
 * 职责细化--分为ICourseInfo、ICourseManager两个顶层接口
 */
public interface ICourse {

    /**
     * 获取课程名称
     */
    String getCourseName();

    /**
     * 获取课程视频
     */
    byte[] getCourseVideo();

    /**
     * 学习课程
     */
    void studyCourse();

    /**
     * 课程退款
     */
    void refundCourse();

}
