package com.hzg.principle.carp;

/**
 * @Package: com.hzg.principle.carp
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-05 18:00
 */
public class ProductDao {

    private DBConnection dbConnection;

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addProduct() {
        String connection = this.dbConnection.getConnection();
        System.out.println("================使用[" + connection + "]增加产品================");
    }

}
