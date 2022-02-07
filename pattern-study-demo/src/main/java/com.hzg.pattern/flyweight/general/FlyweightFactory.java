package com.hzg.pattern.flyweight.general;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.hzg.pattern.flyweight.general
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-12 15:47
 */
public class FlyweightFactory {

    private static Map<String, IFlyweight> pool = new HashMap<String, IFlyweight>();

    public static IFlyweight getFlyweight(String intrinsicState) {
        if (!pool.containsKey(intrinsicState)) {
            IFlyweight flyweight = new ConcreteFlyweight(intrinsicState);
            pool.put(intrinsicState, flyweight);
        }
        return pool.get(intrinsicState);
    }

}
