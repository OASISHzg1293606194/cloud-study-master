package com.hzg.interview.first;

/**
 * @Package: com.hzg.interview.first
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-10-05 23:12
 */
public class MemberVariable {
    static int s;
    int i;
    int j;

    {
        int i = 1;
        i++;
        j++;
        s++;
    }

    public void test(int j) {
        j++;
        i++;
        s++;
    }

    /**
     * 就近原则
     * <p/>
     * 变量的分类<br/>
     * 成员变量：类变量、实例变量<br/>
     * 局部变量<br/>
     * 局部变量与成员变量的区别：<br/>
     * // 1、声明的位置：<br/>
     * //      局部变量：方法体{}中、形参、代码块{}中<br/>
     * //      成员变量：类中方法外<br/>
     * //          类变量：有static修饰<br/>
     * //          实例变量：没有static修饰<br/>
     * // 2、修饰符<br/>
     * //      局部变量：final<br/>
     * //      成员变量：public、protected、private、final、static、volatile、transient<br/>
     * // 3、值存储的位置<br/>
     * //      局部变量：栈<br/>
     * //      实例变量：堆<br/>
     * //      类变量：方法区<br/>
     * // 4、作用域<br/>
     * //      局部变量：从声明处开始，到所属的}结束<br/>
     * //      实例变量：在当前类中"this."(有时this.可以缺省)，在其他类中“对象名.”访问<br/>
     * //      类变量：在当前类中“类名.”(有时类名，可以省略)，在其他类中“类名.”或“对象名.”访问<br/>
     * // 5、生命周期<br/>
     * //      局部变量：每一个线程，每一次调用执行都是新的生命周期<br/>
     * //      实例变量：随着对象的创建而初始化，随着对象的回收而消亡，每一个对象的实例变量是独立的<br/>
     * //      类变量：随着类的初始化而初始化，随着类的卸载而消亡，该类的所有对象的类变量是共享的
     * <p/>
     * 非静态代码块的执行：每次创建实例对象都会执行
     * <p/>
     * 方法的调用规则：调用一次执行一次
     * <p/>
     *
     * @param args
     * @return void
     * @author HuangZhiGao
     * @date 2021/10/5/005 23:21
     */
    public static void main(String[] args) {
        MemberVariable m1 = new MemberVariable();
        MemberVariable m2 = new MemberVariable();
        m1.test(10);
        m1.test(20);
        m2.test(30);
        System.out.println(m1.i + "," + m1.j + "," + m1.s);
        System.out.println(m2.i + "," + m2.j + "," + m2.s);
    }

}
