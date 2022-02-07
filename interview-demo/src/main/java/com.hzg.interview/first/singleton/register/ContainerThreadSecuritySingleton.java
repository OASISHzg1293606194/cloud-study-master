package com.hzg.interview.first.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式单例--解决线程安全问题
 *
 * @author HaungZhiGao
 * @create  2020-07-16 22:15
 */
public class ContainerThreadSecuritySingleton {

    private ContainerThreadSecuritySingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>(16);

    public static Object getInstance(String clazzName) {
        if (ioc.containsKey(clazzName)) {
            return ioc.get(clazzName);
        } else {
            if (ioc.containsKey(clazzName)) {
                return ioc.get(clazzName);
            }

            synchronized (ContainerThreadSecuritySingleton.class) {
                Object instance = null;
                try {
                    if (ioc.containsKey(clazzName)) {
                        instance = ioc.get(clazzName);
                    } else {
                        instance = Class.forName(clazzName).newInstance();
                        ioc.put(clazzName, instance);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return instance;
            }
        }
    }

    /**
     * 方法二:利用ConcurrentHashMap#putIfAbsent()方法的原子性
     */
    public static Object getInstance2(String clazzName) {
        Object instance = null;
        try {
            ioc.putIfAbsent(clazzName, Class.forName(clazzName).newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ioc.get(clazzName);
    }

}
