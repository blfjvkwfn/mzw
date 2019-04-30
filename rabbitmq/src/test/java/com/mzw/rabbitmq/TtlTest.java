package com.mzw.rabbitmq;

import com.mzw.rabbitmq.config.Constant;
import com.mzw.rabbitmq.config.Consumer;
import com.mzw.rabbitmq.config.QueueConfig;
import com.mzw.rabbitmq.config.QueueMessage;
import com.mzw.rabbitmq.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author Jonathan Meng
 * @date 29/04/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TtlTest {
    @Autowired
    private RabbitTemplate normalRabbitTemplate;

    @Test
    public void test() throws InterruptedException {
        Consumer.countDownLatch = new CountDownLatch(4);
        normalRabbitTemplate.convertAndSend(JsonUtil.bean2Json(new QueueMessage("1", Constant.FAILED_MESSAGE)));
        Consumer.countDownLatch.await();
    }
}
