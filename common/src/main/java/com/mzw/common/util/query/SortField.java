package com.mzw.common.util.query;

import lombok.Data;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/6 17:15
 */
@Data
public class SortField {
    public static final String SORT_TYPE_ASC = "asc";
    public static final String SORT_TYPE_DESC = "desc";
    private String field;
    private String type;
}
