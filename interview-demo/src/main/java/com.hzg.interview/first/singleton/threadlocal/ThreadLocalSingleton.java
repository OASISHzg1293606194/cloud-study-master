package com.hzg.interview.first.singleton.threadlocal;

/**
 * ThreadLocal单例<br/>
 * 保证线程内部的全局唯一，且天生线程安全
 *
 * @author HaungZhiGao
 * @create  2020-07-16 23:21
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance =
            new ThreadLocal<ThreadLocalSingleton>() {
                @Override
                protected ThreadLocalSingleton initialValue() {
                    return new ThreadLocalSingleton();
                }
            };

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance() {
        return threadLocalInstance.get();
    }
}
