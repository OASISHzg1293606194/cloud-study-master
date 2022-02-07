package com.hzg.pattern.decorator.example;

/**
 * @Package: com.hzg.pattern.decorator.example
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 17:31
 */
public class Test {

    public static void main(String[] args) {
        StandardBatterCake standardBatterCake = new StandardBatterCake();
        System.out.println("================煎饼果子标配================");
        System.out.println(standardBatterCake.getName() + "_" + standardBatterCake.getPrice());

        BatterCakeDecorator batterCakeDecoratorWithEgg = new BatterCakeWithEggDecorator(standardBatterCake);
        BatterCakeDecorator batterCakeDecoratorWithLettuce = new BatterCakeWithLettuceDecorator(batterCakeDecoratorWithEgg);
        BatterCakeDecorator batterCakeDecoratorWithSausage = new BatterCakeWithSausageDecorator(batterCakeDecoratorWithLettuce);
        System.out.println("================煎饼果子+1个鸡蛋+1份生菜+1根香肠================");
        System.out.println(batterCakeDecoratorWithSausage.getName() + "_" + batterCakeDecoratorWithSausage.getPrice());
    }

}
