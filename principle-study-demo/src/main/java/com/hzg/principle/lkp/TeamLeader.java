package com.hzg.principle.lkp;


import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.hzg.principle.lkp
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 17:08
 */
public class TeamLeader {

    public void checkNumberOfCourses(List<Course> courseList) {
        System.out.println(courseList.size());
    }

    public void checkNumberOfCourses() {
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            courseList.add(new Course());
        }
        System.out.println(courseList.size());
    }

}
