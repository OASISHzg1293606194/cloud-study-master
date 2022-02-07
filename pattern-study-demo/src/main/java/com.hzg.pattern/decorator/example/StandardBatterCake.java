package com.hzg.pattern.decorator.example;

/**
 * @Package: com.hzg.pattern.decorator.example
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 16:59
 */
public class StandardBatterCake extends BatterCake {

    @Override
    protected String getName() {
        return "煎饼";
    }

    @Override
    protected Double getPrice() {
        return new Double(5);
    }

}
