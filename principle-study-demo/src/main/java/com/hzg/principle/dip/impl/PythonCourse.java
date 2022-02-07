package com.hzg.principle.dip.impl;

import com.hzg.principle.dip.ICourse;

/**
 * @Package: com.hzg.principle.dip.impl
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 15:15
 */
public class PythonCourse implements ICourse {
    @Override
    public void study() {
        System.out.println("学习Python课程");
    }
}
