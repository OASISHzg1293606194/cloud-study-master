package com.hzg.pattern.flyweight.ticket;

/**
 * @Package: com.hzg.pattern.flyweight.ticket
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-12 16:03
 */
public class Test {

    public static void main(String[] args) {
        ITicket ticket = TicketFactory.getTicket("北京西", "长沙南");
        ticket.showInfo("硬座");

        ticket = TicketFactory.getTicket("北京西", "长沙南");
        ticket.showInfo("软卧");

        ticket = TicketFactory.getTicket("长沙南", "深圳北");
        ticket.showInfo("硬卧");
    }

}
