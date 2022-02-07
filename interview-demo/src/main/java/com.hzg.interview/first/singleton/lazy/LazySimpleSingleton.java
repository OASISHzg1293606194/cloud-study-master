package com.hzg.interview.first.singleton.lazy;

/**
 * 懒汉单例：简单写法
 *
 * @author HaungZhiGao
 * @create  2020-07-13 22:16
 */
public class LazySimpleSingleton {
    private static LazySimpleSingleton instance;

    private LazySimpleSingleton() {
    }

    // getInstance方法加synchronized关键字解决线程不安全问题
    public static LazySimpleSingleton getInstance() {
        if (instance == null) {
            instance = new LazySimpleSingleton();
        }
        return instance;
    }
}
