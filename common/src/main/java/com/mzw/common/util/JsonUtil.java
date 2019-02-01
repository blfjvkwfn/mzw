package com.mzw.common.util;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/2/1 16:52
 */
public class JsonUtil {
    public static String bean2Json(Object object) {
        return JSON.toJSONString(object);
    }
    public static<T> T json2Bean(String jsonStr, Class<T> targetClass) {
        return JSON.parseObject(jsonStr, targetClass);
    }
}
