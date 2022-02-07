package com.hzg.interview.first;


/**
 * @Package: com.hzg.interview.first
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-05 16:34
 */
public class JseInterviewDemo {

    /**
     * 变量自增
     * <p/>
     * 赋值=，最后计算<br/>
     * =右边的从左到右加载值依次压入操作数栈<br/>
     * 实际先算哪个，看运算符优先级<br/>
     * 自增、自减操作都是直接修改变量的值，不经过操作数栈<br/>
     * 最后的赋值之前，临时结果也是存储在操作数栈中<br/>
     * <p/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2021/10/5/005 16:41
     */
    private static void variableIncrement() {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }

    /**
     * 饿汉式单例用枚举最简便
     * <p/>
     * 懒汉式单例用静态匿名内部类最简便
     *
     * @return void
     * @author HuangZhiGao
     * @date 2021/10/5/005 17:29
     */
    private static void singleton() {
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
     * @return void
     * @author HuangZhiGao
     * @date 2021/10/5/005 17:49
     */
    private static void clazzInit() {
    }

    /**
     * 方法的参数传递机制<br/>
     * 形参是基本数据类型：传递数据值<br/>
     * 形参是引用数据类型：传递地址值；特殊类型：String、包装器类等对象的不可变性<br/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2021/10/5/005 19:13
     */
    private static void methodTransmitArgument() {
    }

    /**
     * Spring Bean的作用域<br/>
     * 可以通过scope属性来指定bean的作用域<br/>
     * //   singleton：默认值。当IOC容器一创建就会创建bean的实例，而且是单例的，每次得到的都是同一个<br/>
     * //   prototype：原型的。当IOC容器一创建不再实例化该bean，每次调用getBean方法时再实例化该bean，而且每调用一次创建一个对象<br/>
     * //   request：每次请求实例化一个bean<br/>
     * //   session：在一次会话中共享一个bean<br/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2021/10/5/005 23:49
     */
    private static void springBeanScope() {
    }

    public static void main(String[] args) {
        // variableIncrement();
        clazzInit();
    }
}
