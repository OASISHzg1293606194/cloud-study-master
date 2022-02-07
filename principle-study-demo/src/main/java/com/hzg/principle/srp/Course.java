package com.hzg.principle.srp;

/**
 * @Package: com.hzg.principle.srp
 * @Description: “全能类”
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 16:16
 */
public class Course {
    public void study(String courseName) {
        if ("直播课".equals(courseName)) {
            System.out.println(courseName + "不能快进看");
        } else {
            System.out.println(courseName + "可以反复回看");
        }
    }
}
