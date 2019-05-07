package com.mzw.pattern.builder;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public interface Item {
    String name();
    Packing packing();
    float price();
}
