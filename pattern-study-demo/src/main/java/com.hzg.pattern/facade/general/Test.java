package com.hzg.pattern.facade.general;

/**
 * @Package: com.hzg.pattern.facade.general
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 12:18
 */
public class Test {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.doA();
        facade.doB();
        facade.doC();
    }
}
