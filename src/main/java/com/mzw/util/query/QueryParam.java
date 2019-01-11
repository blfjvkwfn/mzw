package com.mzw.util.query;

import com.alibaba.fastjson.JSON;
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
    private Sort sort;
    private List<Condition> conditions = new ArrayList<>();
    private boolean pagination = true;

    public static void main(String[] args) {
        QueryParam param = new QueryParam();
        param.getConditions().add(new Condition("id","2","eq"));
        Sort sort = new Sort();
        sort.setName("id");
        sort.setOrder(Sort.SORT_TYPE_ASC);
        param.setSort(sort);
        System.out.println(JSON.toJSON(param));
    }
}
