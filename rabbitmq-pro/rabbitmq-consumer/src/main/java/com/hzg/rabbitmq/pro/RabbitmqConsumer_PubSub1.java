package com.hzg.rabbitmq.pro;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Package: com.hzg.rabbitmq.pro
 * @Description: PubSub订阅模式-RabbitMQ消息消费者
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-15 16:44
 */
public class RabbitmqConsumer_PubSub1 {
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

            // 6、消费消息
            /**
             * queue        队列名称
             * autoAck      是否自动确认
             * callback     回调对象-监听
             */
            String exchangeName = "oasis-exchange-fanout";
            String queueName1 = exchangeName + "-queue1";
            Consumer consumer = getDefaultConsumer(channel);
            channel.basicConsume(queueName1, true, consumer);
            System.out.println("================================>msg receive begin");

            // 不关闭资源-监听并接收消息
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取默认的Consumer
     * <p/>
     * com.rabbitmq.client.DefaultConsumer
     * <p/>
     *
     * @param channel
     * @return com.rabbitmq.client.Consumer
     * @author HuangZhiGao
     * @date 2022/1/19/019 17:49
     */
    private static Consumer getDefaultConsumer(Channel channel) {
        return new DefaultConsumer(channel) {
            /**
             * 回调方法-当收到消息后自动执行
             * <p/>
             *
             * consumerTag 消息标识
             * envelope    获取信息：例如：交换机、路由键……
             * properties  配置信息
             * body        消息内容byte
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("================================>msgBody：" + new String(body));
            }
        };
    }

}
