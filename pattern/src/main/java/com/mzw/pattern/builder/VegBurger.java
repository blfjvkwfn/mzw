package com.mzw.pattern.builder;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "VegBurger";
    }

    @Override
    public float price() {
        return 25F;
    }
}
