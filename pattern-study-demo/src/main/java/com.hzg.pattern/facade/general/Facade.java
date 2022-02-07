package com.hzg.pattern.facade.general;

/**
 * @Package: com.hzg.pattern.facade.general
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 12:17
 */
public class Facade {

    private SubSystemA subSystemA = new SubSystemA();
    private SubSystemB subSystemB = new SubSystemB();
    private SubSystemC subSystemC = new SubSystemC();

    public void doA() {
        subSystemA.doA();
    }

    public void doB() {
        subSystemB.doB();
    }

    public void doC() {
        subSystemC.doC();
    }

    public void unified() {
        subSystemA.doA();
        subSystemB.doB();
        subSystemC.doC();
    }

}
