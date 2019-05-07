package com.mzw.rabbitmq.config;

import com.mzw.rabbitmq.util.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan Meng
 * @date 07/05/2019
 */
@Component
public class TopicProducer {
    private final RabbitTemplate topicRabbitTemplate;

    public TopicProducer(RabbitTemplate topicRabbitTemplate) {
        this.topicRabbitTemplate = topicRabbitTemplate;
    }

    public void send(User user) {
        topicRabbitTemplate.convertAndSend(TopicRabbitConfig.EXCHANGE_NAME, TopicRabbitConfig.ROUTING_KEY, JsonUtil.bean2Json(user));
    }
}
