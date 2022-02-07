package com.hzg.rabbitmq.pro;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Package: com.hzg.rabbitmq.pro
 * @Description: 简单模式-RabbitMQ消息生产者
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-15 16:45
 */
public class RabbitmqProvider {
    public static void main(String[] args) {
        try {
            // 1、创建连接工厂
            ConnectionFactory connectionFactory = new ConnectionFactory();

            // 2、设置参数
            // 服务地址默认为localhost
            connectionFactory.setHost("192.168.213.225");
            // 端口默认为5672
            connectionFactory.setPort(5672);
            // 虚拟机默认为/
            connectionFactory.setVirtualHost("/admin-vhost");
            // 用户名、密码默认均为guest
            connectionFactory.setUsername("admin");
            connectionFactory.setPassword("admin");

            // 3、创建连接 Connection
            Connection connection = connectionFactory.newConnection();

            // 4、创建信道 Channel
            Channel channel = connection.createChannel();

            // 5、创建队列 Queue
            /**
             * queue        队列名称
             * durable      是否持久化
             * exclusive    是否独占-只能有一个消费者监听队列；当Connection关闭时是否删除队列
             * autoDelete   是否自动删除-没有消费者时自动删除
             * arguments    参数
             */
            channel.queueDeclare("test-queue", true, false, false, null);

            // 6、发送消息
            /**
             * exchange     交换机名称，简单模式下为默认的""
             * routingKey   路由键
             * props        配置信息
             * body         消息内容byte
             */
            String msgContent = "不必说";
            channel.basicPublish("", "test-queue", null, msgContent.getBytes());
            System.out.println("================================>msg send finished");

            // 关闭资源
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
