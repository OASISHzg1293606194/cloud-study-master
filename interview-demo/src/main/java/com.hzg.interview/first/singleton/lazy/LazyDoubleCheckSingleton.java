package com.hzg.interview.first.singleton.lazy;

/**
 * 懒汉单例：双重检查锁
 *
 * @author HaungZhiGao
 * @create  2020-07-13 23:25
 */
public class LazyDoubleCheckSingleton {

    // 此处不加volatile关键字会出现“指令重排序问题”
    // “指令重排序问题”，也就是说先声明instance还是先创建instance实例的问题
    private volatile static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        // 第一次检查是否要阻塞
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                // 第二次检查是否要重新创建实例
                if (instance == null) {
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }

        return instance;
    }

}
