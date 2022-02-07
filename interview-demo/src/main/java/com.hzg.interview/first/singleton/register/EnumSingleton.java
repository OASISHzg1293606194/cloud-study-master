package com.hzg.interview.first.singleton.register;

/**
 * 枚举式单例<br/>
 * 意味着继承了Enum类，如下<br/>
 * public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable
 *
 * @author HaungZhiGao
 * @create  2020-07-16 21:05
 */
public enum EnumSingleton {
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
