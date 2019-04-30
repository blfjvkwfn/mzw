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
import java.util.Objects;
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
        Integer retryCount = getRetryCount(message);
        if (FAILED_MESSAGE.equals(queueMessage.getMessage())) {
            sendToTTL(body, retryCount);
        }
    }

    private void sendToTTL(String body, Integer retryCount) {
        Integer nextRryCount = Objects.isNull(retryCount) ? 1 : ++retryCount;
        if (nextRryCount > MAX_RETRY_COUNT) {
            log.info("Reach the maximum number of retries, message:{}", body);
            return;
        }

        log.info("send to ttl queue, message:{}", body);

        Message message = MessageBuilder.withBody(body.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("UTF-8")
                .setHeader(RETRY_COUNT_KEY, nextRryCount)
                .build();

        ttlRabbitTemplate.convertAndSend(message);
    }

    private Integer getRetryCount(Message data) {
        Object retryCount = data.getMessageProperties().getHeaders().get(RETRY_COUNT_KEY);
        if (Objects.isNull(retryCount) || !(retryCount instanceof Integer)) {
            return null;
        }
        return (Integer) retryCount;
    }
}
