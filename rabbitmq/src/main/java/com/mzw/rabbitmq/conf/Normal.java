package com.mzw.rabbitmq.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/2/20 9:55
 */
@Configuration
public class Normal {
    @Bean
    public Queue normalTest() {
        return new Queue("normalTest");
    }
}
