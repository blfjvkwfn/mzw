package com.mzw.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/10/16 16:52
 */
public class TestList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.forEach(System.out::println);

        list.forEach(i -> System.out.println(i + 3));
    }
}
