package com.mzw.common.util.query;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/6 10:56
 */
public enum QueryType {
    EQUAL("eq","equal"),
    LESS_THAN_OR_EQUAL_TO("lt","lessThanOrEqualTo"),
    GREATER_THAN_OR_EQUAL_TO("gt","greaterThanOrEqualTo"),
    LIKE("li","like"),
    IN("in","in");
    private String type;
    private String method;

    QueryType(String type, String method) {
        this.type = type;
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public static QueryType getByType(String type) {
        for (QueryType queryType : values()) {
            if (queryType.getType().equals(type)) {
                return queryType;
            }
        }

        return null;
    }

    public String getMethod() {
        return method;
    }
}
