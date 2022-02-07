package com.hzg.pattern.decorator.example;

/**
 * @Package: com.hzg.pattern.decorator.example
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 17:01
 */
public class BatterCakeWithEggDecorator extends BatterCakeDecorator {

    public BatterCakeWithEggDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected String getName() {
        return super.getName() + "+1个鸡蛋";
    }

    @Override
    protected Double getPrice() {
        return super.getPrice() + new Double(1.5);
    }

}
