package com.mzw.pattern.builder;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "ChickenBurger";
    }

    @Override
    public float price() {
        return 50.5F;
    }
}
