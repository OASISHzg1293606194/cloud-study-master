package com.hzg.crawler.demo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Package: com.hzg.crawler.demo
 * @Description: 队列Queue用法
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-15 11:00
 */
public class QueueTest {

    public static void main(String[] args) {
        // 队列是一种特殊的线性表，它只允许在表的前端进行删除操作，而在表的后端进行插入操作
        // LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用
        //                           抛出异常    返回特殊值
        // 添加元素                   add(e)     offer(e)
        // 移除元素                   remove()   poll()
        // 获取队头元素但不移除队头元素 element()  peek()

        // 先进先出
        Queue<String> queue = new LinkedList<String>();
        // 添加元素
        queue.offer("1001");
        queue.offer("1002");
        queue.offer("1003");
        queue.offer("1004");
        for (String temp : queue) {
            System.out.println(temp);
        }
        System.out.println("================================");
        System.out.println("返回队列头元素，并从队列中移除：" + queue.poll());
        System.out.println("================================");
        System.out.println("返回队列头元素，但不从队列移除：" + queue.element());
        for (String temp : queue) {
            System.out.println(temp);
        }
        System.out.println("================================");
        System.out.println("返回队列头元素，但不从队列移除：" + queue.peek());
        for (String temp : queue) {
            System.out.println(temp);
        }
        System.out.println("================================");
        System.out.println(queue.size());
    }

}
