package com.hzg.jmx;

/**
 * @Package: com.hzg.jmx
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-02-11 13:20
 */
public class Hello implements HelloMBean {

    private String bizId;

    private String name;


    public String getBizId() {
        System.out.println("execute getBizId");
        return bizId;
    }

    public void setBizId(String bizId) {
        System.out.println("execute setBizId");
        this.bizId = bizId;
    }

    public String getName() {
        System.out.println("execute getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("execute setName");
        this.name = name;
    }


    public void sayHelloWorld() {
        // TODO 显示服务监控状态
        // TODO 显示是否能正常连接数据库
        System.out.println("hello world");
    }
}
