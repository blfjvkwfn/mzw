package com.mzw.util.query;

import lombok.Data;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/6 17:15
 */
@Data
public class Sort {
    public static final String SORT_TYPE_ASC = "asc";
    public static final String SORT_TYPE_DESC = "desc";
    private String name;
    private String order;

    public Sort(String name, String order) {
        this.name = name;
        this.order = order;
    }

    public Sort() {
    }
}
