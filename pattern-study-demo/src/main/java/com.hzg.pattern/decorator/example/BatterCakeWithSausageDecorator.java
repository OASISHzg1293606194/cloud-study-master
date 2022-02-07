package com.hzg.pattern.decorator.example;

/**
 * @Package: com.hzg.pattern.decorator.example
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 17:02
 */
public class BatterCakeWithSausageDecorator extends BatterCakeDecorator {

    public BatterCakeWithSausageDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected String getName() {
        return super.getName() + "+1根香肠";
    }

    @Override
    protected Double getPrice() {
        return super.getPrice() + new Double(2);
    }

}
