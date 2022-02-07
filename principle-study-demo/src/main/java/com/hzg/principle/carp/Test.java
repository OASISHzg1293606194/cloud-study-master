package com.hzg.principle.carp;

import com.hzg.principle.carp.dbconn.MySQLConnection;
import com.hzg.principle.carp.dbconn.OracleConnection;

/**
 * @Package: com.hzg.principle.carp
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 18:08
 */
public class Test {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        productDao.setDbConnection(new MySQLConnection());
        productDao.addProduct();
        productDao.setDbConnection(new OracleConnection());
        productDao.addProduct();
    }
}
