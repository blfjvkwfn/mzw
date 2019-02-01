package com.mzw.common.util.query;

import lombok.Data;

import java.util.List;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/7 15:31
 */
@Data
public class Pager<T> {
    private int totalPages = 0;
    private long totalElements = 0L;
    private List<T> content;
}
