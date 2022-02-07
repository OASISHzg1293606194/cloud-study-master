package com.hzg.interview.first.clazzinit;

/**
 * @Package: com.hzg.interview.first.clazzinit
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-05 17:36
 */
public class Son extends Father {

    private int i = test();
    private static int j = method();


    static {
        System.out.print("(6)");
    }

    Son() {
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }

    @Override
    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method() {
        System.out.print("(10)");
        return 1;
    }

    /**
     * 【类初始化过程】<br/>
     * 一个类要创建实例需要先加载并初始化该类<br/>
     * main方法所在的类需要先加载和初始化<br/>
     * 一个子类要初始化需要先初始化其父类<br/>
     * 一个类初始化就是执行<clinit>()方法<br/>
     * <clinit>()方法由静态类变量显示赋值代码和静态代码块组成<br/>
     * 静态类变量显示赋值代码和静态代码块代码从上到下顺序执行<br/>
     * <clinit>()方法只执行一次
     * <p/>
     * <p>
     * 【实例初始化过程】
     * <p/>
     * 实例初始化就是执行<init>()方法<br/>
     * <init>()方法可能重载有多个，有几个构造器就有几个<init>()方法<br/>
     * <init>()方法由非静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成<br/>
     * 非静态实例变量显示赋值代码和非静态代码块代码从上到下顺序执行，而对应构造器的代码最后执行<br/>
     * 每次创建实例对象，调用对应构造器，执行的就是对应的<init>()方法<br/>
     * <init>()方法的首行是super()或super(实参列表)，即对应父类的<init>()方法<br/>
     * super();// 子类构造器写或不写，子类构造器也一定会调用父类的构造器
     * <p/>
     * <p>
     * 【方法的重写Override】
     * <p/>
     * 重写非静态方法，不会重写final方法、静态方法、private等子类不可见方法<br/>
     * 面向对象的多态<br/>
     * 子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的代码<br/>
     * 非静态方法默认的调用对象是this<br/>
     * this对象在构造器或者说<init>()方法中就是正在创建的对象
     * <p/>
     *
     * @param args
     * @return void
     * @author HuangZhiGao
     * @date 2021/10/5/005 18:08
     */
    public static void main(String[] args) {
        // 运行当前子类的空main方法--会分别依次执行父类、子类的clinit方法(通过字节码文件查看)
        // (5)(1)(10)(6)

        Son son1 = new Son();
        System.out.println();
        Son son2 = new Son();
        // (5)(1)(10)(6)(9)(3)(2)(9)(8)(7)
        // (9)(3)(2)(9)(8)(7)
    }

}
