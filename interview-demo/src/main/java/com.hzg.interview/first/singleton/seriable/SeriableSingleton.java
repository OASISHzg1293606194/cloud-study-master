package com.hzg.interview.first.singleton.seriable;

import java.io.Serializable;

/**
 * 序列化和反序列化破坏单例：以饿汉式为例<br/>
 *
 * @author HaungZhiGao
 * @create  2020-07-16 22:39
 */
public class SeriableSingleton implements Serializable {
    public static final SeriableSingleton INSTANCE = new SeriableSingleton();

    private SeriableSingleton() {
    }

    public static SeriableSingleton getInstance() {
        return INSTANCE;
    }

    /**
     * 不加这个方法会导致序列化和反序列化破坏单例<br/>
     * 方法名必须为readResolve
     *
     * @return
     */
    private Object readResolve() {
        return INSTANCE;
    }
}
