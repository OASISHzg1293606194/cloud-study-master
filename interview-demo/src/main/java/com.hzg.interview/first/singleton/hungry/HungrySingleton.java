package com.hzg.interview.first.singleton.hungry;

/**
 * 饿汉式单例：写法一
 *
 * @author HaungZhiGao
 * @create  2020-07-13 21:53
 */
public class HungrySingleton {
    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
