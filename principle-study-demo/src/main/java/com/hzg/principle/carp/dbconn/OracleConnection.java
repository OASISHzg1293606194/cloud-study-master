package com.hzg.principle.carp.dbconn;

import com.hzg.principle.carp.DBConnection;

/**
 * @Package: com.hzg.principle.carp.dbconn
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 17:58
 */
public class OracleConnection extends DBConnection {

    @Override
    public String getConnection() {
        return "Oracle 数据库连接";
    }

}
