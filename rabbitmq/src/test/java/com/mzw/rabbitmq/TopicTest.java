package com.mzw.rabbitmq;

import com.mzw.rabbitmq.config.TopicProducer;
import com.mzw.rabbitmq.config.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jonathan Meng
 * @date 07/05/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {
    @Autowired
    private TopicProducer topicProducer;

    @Test
    public void send() throws InterruptedException {
        topicProducer.send(new User("1",1,"Jonathan"));
        topicProducer.send(new User("2",2,"Jonathan1"));
        topicProducer.send(new User("3",3,"Jonathan3"));
    }
}
