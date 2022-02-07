package com.hzg.pattern.decorator.example;

/**
 * @Package: com.hzg.pattern.decorator.example
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 17:24
 */
public abstract class BatterCakeDecorator extends BatterCake {

    private BatterCake batterCake;

    public BatterCakeDecorator(BatterCake batterCake) {
        this.batterCake = batterCake;
    }

    @Override
    protected String getName() {
        return this.batterCake.getName();
    }

    @Override
    protected Double getPrice() {
        return this.batterCake.getPrice();
    }

}
