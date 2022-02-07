package com.hzg.rabbitmq.pro;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Package: com.hzg.rabbitmq.pro
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-24 10:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProviderTest {

    @Resource
    private RabbitTemplate rabbitTemplate;


    @After
    public void afterSignal() {
        System.out.println("========================> was finished");
    }

    @Test
    public void simpleSend() {
        rabbitTemplate.convertAndSend("spring_queue", "hello rabbitmq");
    }

    @Test
    public void fanoutSend() {
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "fanout: hello rabbitmq");
    }

    @Test
    public void topicsSend() {
        rabbitTemplate.convertAndSend("spring_topic_exchange", "oasis.midnight", "topics: hello rabbitmq");
        rabbitTemplate.convertAndSend("spring_topic_exchange", "oasis.red.dress", "topics: hello rabbitmq");
        rabbitTemplate.convertAndSend("spring_topic_exchange", "ocean.gulf.pinkbeach", "topics: hello rabbitmq");
    }

}
