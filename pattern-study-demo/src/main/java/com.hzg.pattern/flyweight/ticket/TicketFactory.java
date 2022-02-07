package com.hzg.pattern.flyweight.ticket;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.hzg.pattern.flyweight.ticket
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-12 15:59
 */
public class TicketFactory {

    private static Map<String, ITicket> ticket_pool = new HashMap<String, ITicket>();

    public static ITicket getTicket(String from, String to) {
        String key = from + "==>" + to;
        if (ticket_pool.containsKey(key)) {
            System.out.println("================使用缓存================");
            return ticket_pool.get(key);
        }
        ITicket ticket = new TrainTicket(from, to);
        ticket_pool.put(key, ticket);
        System.out.println("================首次查询，创建对象[" + key + "]================");
        return ticket_pool.get(key);
    }

}
