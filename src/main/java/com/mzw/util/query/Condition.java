package com.mzw.util.query;

import lombok.Data;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/6 10:48
 */
@Data
public class Condition {
    private String field;
    private Object value;
    private String queryType;

    public Condition() {
    }

    public Condition(String field, Object value, String queryType) {
        this.field = field;
        this.value = value;
        this.queryType = queryType;
    }
}
