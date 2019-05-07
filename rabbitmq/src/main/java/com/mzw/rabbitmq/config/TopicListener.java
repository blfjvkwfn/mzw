package com.mzw.rabbitmq.config;

import com.mzw.rabbitmq.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan Meng
 * @date 07/05/2019
 */
@Component
@Slf4j
public class TopicListener {

    @RabbitListener(queues = TopicRabbitConfig.QUEUE_NAME)
    public void process(String user) {
        log.info("Receive data:" + user);

        throw new RuntimeException("**************");
    }

    @RabbitListener(queues = TopicRabbitConfig.QUEUE_NAME)
    public void process1(String user) {
        log.info("Receive data1:" + user);

        throw new RuntimeException("**************");
    }
}
