package com.hzg.pattern.flyweight.pool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

/**
 * @Package: com.hzg.pattern.flyweight.pool
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-12 17:57
 */
public class ConnectionPool {

    private Vector<Connection> pool;
    private int poolSize = 100;

    private String jdbcUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private String username = "root";
    private String password = "root";
    private String driverClazz = "org.gjt.mm.mysql.Driver";


    public ConnectionPool() {
        pool = new Vector<Connection>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(driverClazz);
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                pool.add(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Connection getConnection() {
        if (pool.size() > 0) {
            Connection connection = pool.get(0);
            pool.remove(connection);
            return connection;
        }
        return null;
    }

    public synchronized void release(Connection connection) {
        pool.add(connection);
    }

}
