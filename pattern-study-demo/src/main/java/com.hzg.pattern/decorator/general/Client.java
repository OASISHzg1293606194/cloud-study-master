package com.hzg.pattern.decorator.general;

/**
 * @Package: com.hzg.pattern.decorator.general
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 14:45
 */
public class Client {
    public static void main(String[] args) {
        ConcreteComponent concreteComponent = new ConcreteComponent();
        System.out.println("================ConcreteDecoratorA================");
        Decorator decoratorA = new ConcreteDecoratorA(concreteComponent);
        decoratorA.operation();
        System.out.println("================ConcreteDecoratorB================");
        Decorator decoratorB = new ConcreteDecoratorB(concreteComponent);
        decoratorB.operation();
        System.out.println("================ConcreteDecoratorB Bind ConcreteDecoratorA================");
        Decorator decoratorBindA = new ConcreteDecoratorB(decoratorA);
        decoratorBindA.operation();
    }
}
