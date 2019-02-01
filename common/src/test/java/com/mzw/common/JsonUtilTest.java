package com.mzw.common;

import com.mzw.common.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/2/1 17:04
 */
public class JsonUtilTest {
    @Test
    public void bean2Json() {
        String json = JsonUtil.bean2Json(Arrays.asList("a","b"));
        System.out.println(json);
        Assert.assertNotNull(json);
    }

    @Test
    public void json2Bean() {
        List<String> list = JsonUtil.json2Bean("[\"a\",\"b\"]", List.class);
        Assert.assertEquals(2, list.size());
    }
}
