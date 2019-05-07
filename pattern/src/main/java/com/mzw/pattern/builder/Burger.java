package com.mzw.pattern.builder;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
