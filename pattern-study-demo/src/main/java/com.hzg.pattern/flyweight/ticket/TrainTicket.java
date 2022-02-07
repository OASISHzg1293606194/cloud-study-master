package com.hzg.pattern.flyweight.ticket;

import java.util.Random;

/**
 * @Package: com.hzg.pattern.flyweight.ticket
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-12 15:57
 */
public class TrainTicket implements ITicket {

    private String from;
    private String to;
    private int price;


    public TrainTicket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void showInfo(String bunk) {
        this.price = new Random().nextInt(500);
        System.out.println(this.from + "==>" + this.to + ":" + bunk + "价格:" + this.price);
    }

}
