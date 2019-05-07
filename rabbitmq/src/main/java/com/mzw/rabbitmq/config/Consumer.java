package com.mzw.rabbitmq.config;

import com.mzw.rabbitmq.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author Jonathan Meng
 * @date 29/04/2019
 */
@Component
@Slf4j
@Lazy(false)
public class Consumer {
    private static final Charset DEFAULT_CHARSET = Constant.DEFAULT_CHARSET;
    private static final String FAILED_MESSAGE = Constant.FAILED_MESSAGE;
    private static final String RETRY_COUNT_KEY = Constant.RETRY_COUNT_KEY;
    private static final Integer MAX_RETRY_COUNT = Constant.MAX_RETRY_COUNT;
    private final static String X_DEATH = "x-death";
    private final static String X_DEATH_COUNT = "count";

    public static CountDownLatch countDownLatch;

    @Resource(name = "ttlRabbitTemplate")
    private RabbitTemplate ttlRabbitTemplate;

    @RabbitListener(queues = {"#{normalQueue.name}"})
    public void process(Message message) {
        String body = new String(message.getBody(), DEFAULT_CHARSET);
        log.info("receive message:{}", body);
        if (Objects.isNull(body)) {
            return;
        }

        if (Objects.nonNull(countDownLatch)) {
            countDownLatch.countDown();
        }
        QueueMessage queueMessage = JsonUtil.json2Bean(body, QueueMessage.class);
        Long retryCount = getRetryCount(message);
        if (FAILED_MESSAGE.equals(queueMessage.getMessage())) {
            sendToTTL(body, retryCount,message);
        }
    }

    private void sendToTTL(String body, Long retryCount, Message oldMessage) {
        if (Objects.isNull(retryCount)) {
            log.warn("First time send message to delay queue");
            ttlRabbitTemplate.convertAndSend(body);
        } else {
            if (MAX_RETRY_COUNT <= retryCount) {
                log.error("Number of retries has reached the maximum [{}]", retryCount);
                return;
            }
            log.warn("Send message to delay queue, retry count [{}]", retryCount);
            ttlRabbitTemplate.convertAndSend(oldMessage);
        }
    }

    private Long getRetryCount(Message data) {
        Map<String, Object> headers = data.getMessageProperties().getHeaders();
        if (headers.containsKey(X_DEATH)) {
            return (Long) ((Map) ((List) headers.get(X_DEATH)).get(0)).get(X_DEATH_COUNT);
        }

        return null;
    }
}
