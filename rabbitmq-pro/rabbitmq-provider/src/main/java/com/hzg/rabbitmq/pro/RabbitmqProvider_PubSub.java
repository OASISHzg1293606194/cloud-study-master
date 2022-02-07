package com.hzg.rabbitmq.pro;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Package: com.hzg.rabbitmq.pro
 * @Description: PubSub订阅模式-RabbitMQ消息生产者
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-15 16:45
 */
public class RabbitmqProvider_PubSub {
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

            // 5、创建交换机
            /**
             * exchange     交换机名称
             * type         交换机类型 BuiltinExchangeType 4种[DIRECT("direct"), FANOUT("fanout"), TOPIC("topic"), HEADERS("headers")]
             * durable      是否持久化
             * autoDelete   是否自动删除
             * internal     内部使用，一般设置false
             * arguments    参数列表
             */
            String exchangeName = "oasis-exchange-fanout";
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT, true, false, false, null);

            // 6、创建队列
            String queueName1 = exchangeName + "-queue1";
            String queueName2 = exchangeName + "-queue2";
            channel.queueDeclare(queueName1, true, false, false, null);
            channel.queueDeclare(queueName2, true, false, false, null);

            // 7、绑定交换机和队列
            /**
             * queue        队列名称
             * exchange     交换机名称
             * routingKey   路由键-绑定规则：fanout的routingKey为""
             */
            channel.queueBind(queueName1, exchangeName, "");
            channel.queueBind(queueName2, exchangeName, "");

            // 8、发送消息
            String msgContent = "良辰好景更与何人说";
            channel.basicPublish(exchangeName, "", null, msgContent.getBytes());
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
