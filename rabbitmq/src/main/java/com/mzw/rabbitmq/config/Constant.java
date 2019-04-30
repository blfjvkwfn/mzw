package com.mzw.rabbitmq.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Jonathan Meng
 * @date 29/04/2019
 */
public class Constant {
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static final String FAILED_MESSAGE = "FAILED";
    public static final String RETRY_COUNT_KEY = "RETRY_COUNT_KEY";
    public static final Integer MAX_RETRY_COUNT = 3;
}
