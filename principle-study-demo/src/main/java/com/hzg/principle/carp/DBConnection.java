package com.hzg.principle.carp;

/**
 * @Package: com.hzg.principle.carp
 * @Description: 获取数据库连接-顶层抽象
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 17:54
 */
public abstract class DBConnection {

    /**
     * 获取数据库连接
     */
    public abstract String getConnection();

}
