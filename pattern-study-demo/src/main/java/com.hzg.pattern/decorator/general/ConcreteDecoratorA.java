package com.hzg.pattern.decorator.general;

/**
 * @Package: com.hzg.pattern.decorator.general
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-08 14:39
 */
public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        operationFirst();
        super.operation();
        operationLast();
    }

    private void operationFirst() {
        System.out.println("A operationFirst");
    }

    private void operationLast() {
        System.out.println("A operationLast");
    }

}
