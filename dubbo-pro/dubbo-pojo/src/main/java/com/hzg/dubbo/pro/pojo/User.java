package com.hzg.dubbo.pro.pojo;

import java.io.Serializable;

/**
 * @Package: com.hzg.dubbo.pro.pojo
 * @Description: 验证Dubbo序列化反序列化
 * @Author: HuangZhiGao
 * @CreateDate: 2021-12-27 18:41
 */
public class User implements Serializable {

    private Integer bizId;
    private String username;
    private String password;


    public User() {
    }

    public User(Integer bizId, String username, String password) {
        this.bizId = bizId;
        this.username = username;
        this.password = password;
    }


    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
