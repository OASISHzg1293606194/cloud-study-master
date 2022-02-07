package com.hzg.principle.lkp;


import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.hzg.principle.lkp
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 17:08
 */
public class Boss {

    public void commandCheckNumber(TeamLeader teamLeader) {
        /** 不符合LKP */
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            courseList.add(new Course());
        }
        teamLeader.checkNumberOfCourses(courseList);
    }

    public void commandCheckNumberCorrect(TeamLeader teamLeader) {
        /** 符合LKP */
        teamLeader.checkNumberOfCourses();
    }

}
