package com.hzg.dubbo.consumer;

import com.hzg.dubbo.api.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Package: com.hzg.dubbo.consumer
 * @Description: 服务消费者主启动类
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-23 0:25
 */
public class EchoConsumer {

    public static void main(String[] args) {
        // 加载配置
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/echo-demo-consumer.xml"});
        // 启动Spring容器
        context.start();

        // 获取消费代理
        EchoService echoService = (EchoService) context.getBean("echoDemoService");
        // 调用远程方法
        String echo = echoService.echo("Dubbo: Hello world!");
        System.out.println("echo result: " + echo);
    }

}
