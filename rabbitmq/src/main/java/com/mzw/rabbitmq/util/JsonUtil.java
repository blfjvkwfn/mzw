package com.mzw.rabbitmq.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/2/1 16:52
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();
    public static String bean2Json(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static<T> T json2Bean(String jsonStr, Class<T> targetClass) {
        try {
            return mapper.readValue(jsonStr, targetClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
