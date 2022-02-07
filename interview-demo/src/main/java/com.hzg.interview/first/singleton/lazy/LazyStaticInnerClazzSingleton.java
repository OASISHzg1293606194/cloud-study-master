package com.hzg.interview.first.singleton.lazy;

/**
 * 懒汉单例：静态匿名内部类
 *
 * @author HaungZhiGao
 * @create  2020-07-16 19:59
 */
public class LazyStaticInnerClazzSingleton {

    private LazyStaticInnerClazzSingleton() {
        // 防止反射reflect破坏单例
        if (LazyHandler.class != null) {
            throw new RuntimeException("不允许非法访问");
        }
    }

    public static LazyStaticInnerClazzSingleton getInstance() {
        return LazyHandler.INSTANCE;
    }

    private static class LazyHandler {
        private static final LazyStaticInnerClazzSingleton INSTANCE = new LazyStaticInnerClazzSingleton();
    }

    // 程序在加载的时候会去加载classpath下面的.class文件 LazyStaticInnerClazzSingleton.class
    // 而当LazyStaticInnerClazzSingleton的getInstance方法被调用的时候才会去加载关联到的匿名内部类，
    // 即LazyStaticInnerClazzSingleton$LazyHandler.class
}
