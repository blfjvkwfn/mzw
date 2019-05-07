package com.mzw.pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author Jonathan Meng
 * @date 07/05/2019
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class Log4j2HelloWorldExample {
    @Test
    public void test() {
        ThreadContext.put("id", UUID.randomUUID().toString());
        ThreadContext.put("ipAddress", "192.168.21.9");

        log.debug("Debug Message Logged !!");
        log.info("Info Message Logged !!");
        log.debug("Another Debug Message !!");

        //Clear the map
        ThreadContext.clearMap();
        log.debug("Thread Context Cleaned up !!");
        log.debug("Log message with no context information !!");
    }
}
