package com.hzg.interview.first.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式单例
 *
 * @author HaungZhiGao
 * @create  2020-07-16 22:15
 */
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>(16);

    public static Object getInstance(String clazzName) {
        if (ioc.containsKey(clazzName)) {
            return ioc.get(clazzName);
        } else {
            Object instance = null;
            try {
                instance = Class.forName(clazzName).newInstance();
                ioc.put(clazzName, instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return instance;
        }
    }
}
