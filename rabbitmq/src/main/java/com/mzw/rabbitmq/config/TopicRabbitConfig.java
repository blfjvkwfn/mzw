package com.mzw.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jonathan Meng
 * @date 07/05/2019
 */
@Configuration
public class TopicRabbitConfig {
    public static final String QUEUE_NAME = "spring-boot-queue";
    public static final String EXCHANGE_NAME = "spring-boot-exchange";
    public static final String ROUTING_KEY = "spring-boot-key";

    @Bean(name = "topicRabbitTemplate")
    RabbitTemplate topicRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(EXCHANGE_NAME);
        rabbitTemplate.setRoutingKey(ROUTING_KEY);
        rabbitTemplate.setDefaultReceiveQueue(EXCHANGE_NAME);
        return rabbitTemplate;
    }

    @Bean("queueMessage")
    public Queue queueMessage() {
        return new Queue(QUEUE_NAME);
    }

    @Bean("exchange")
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ROUTING_KEY);
    }
}
