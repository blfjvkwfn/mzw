package com.mzw.lambda;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/10/16 17:23
 */
public class TestClass {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("hello world!")).start();
        new Thread(() -> {
            System.out.println("hello world 1!");
            System.out.println("hello world 2!");
        }).start();


    }
}
