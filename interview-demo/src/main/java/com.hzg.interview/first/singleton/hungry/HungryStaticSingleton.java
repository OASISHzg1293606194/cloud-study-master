package com.hzg.interview.first.singleton.hungry;

/**
 * 饿汉式单例：写法二
 *
 * @author HaungZhiGao
 * @create  2020-07-13 21:54
 */
public class HungryStaticSingleton {
    private static final HungryStaticSingleton hungryStaticSingleton;

    static {
        hungryStaticSingleton = new HungryStaticSingleton();
    }

    private HungryStaticSingleton() {
    }

    public static HungryStaticSingleton getInstance() {
        return hungryStaticSingleton;
    }
}
