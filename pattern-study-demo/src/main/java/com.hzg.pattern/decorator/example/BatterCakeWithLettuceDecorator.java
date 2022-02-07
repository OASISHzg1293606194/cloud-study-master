package com.hzg.pattern.decorator.example;

/**
 * @Package: com.hzg.pattern.decorator.example
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 17:04
 */
public class BatterCakeWithLettuceDecorator extends BatterCakeDecorator {

    public BatterCakeWithLettuceDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected String getName() {
        return super.getName() + "+1份生菜";
    }

    @Override
    protected Double getPrice() {
        return super.getPrice() + new Double(1);
    }

}
