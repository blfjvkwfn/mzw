package com.mzw.common.util.query;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/6 10:31
 */
@Getter
@Setter
public class QueryParam {
    private int page = 1;
    private int rows = 10;
    private List<SortField> sortFields = new ArrayList<>(16);
    private List<Condition> conditions = new ArrayList<>(16);
    private boolean pagination = true;
}
