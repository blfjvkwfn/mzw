package com.mzw.pattern.builder;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
