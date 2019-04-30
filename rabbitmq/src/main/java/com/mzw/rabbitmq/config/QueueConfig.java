package com.mzw.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Jonathan Meng
 * @date 29/04/2019
 */
@Configuration
@EnableRabbit
public class QueueConfig {
    @Value("${mzw.rabbitmq.normal.queue}")
    private String normalQueue;
    @Value("${mzw.rabbitmq.normal.exchange}")
    private String normalExchange;
    @Value("${mzw.rabbitmq.normal.routing.key}")
    private String normalRoutingKey;

    @Value("${mzw.rabbitmq.ttl.queue}")
    private String ttlQueue;
    @Value("${mzw.rabbitmq.ttl.exchange}")
    private String ttlExchange;
    @Value("${mzw.rabbitmq.ttl.routing.key}")
    private String ttlRoutingKey;
    @Value("${mzw.rabbitmq.ttl.message}")
    private Integer ttlMessage;

    @Bean(name = "ttlRabbitTemplate")
    RabbitTemplate ttlRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(ttlExchange);
        rabbitTemplate.setRoutingKey(ttlRoutingKey);
        rabbitTemplate.setDefaultReceiveQueue(ttlQueue);
        return rabbitTemplate;
    }

    @Bean
    RabbitTemplate normalRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(normalExchange);
        rabbitTemplate.setRoutingKey(normalRoutingKey);
        rabbitTemplate.setDefaultReceiveQueue(normalQueue);
        return rabbitTemplate;
    }

    @Bean
    Queue normalQueue() {
        return QueueBuilder.durable(normalQueue)
                .build();
    }

    @Bean
    DirectExchange normalExchange() {
        return new DirectExchange(normalExchange);
    }

    @Bean
    Binding normalBinding(Queue normalQueue, DirectExchange normalExchange) {
        return BindingBuilder.bind(normalQueue)
                .to(normalExchange)
                .with(normalRoutingKey);
    }

    @Bean
    DirectExchange ttlExchange() {
        return new DirectExchange(ttlExchange);
    }

    @Bean
    Queue ttlQueue() {
        return QueueBuilder.durable(ttlQueue)
                .withArgument("x-dead-letter-exchange", normalExchange)
                .withArgument("x-dead-letter-routing-key", normalRoutingKey)
                .withArgument("x-message-ttl", ttlMessage)
                .build();
    }

    @Bean
    Binding ttlBinding(Queue ttlQueue, DirectExchange ttlExchange) {
        return BindingBuilder.bind(ttlQueue)
                .to(ttlExchange)
                .with(ttlRoutingKey);
    }
}
