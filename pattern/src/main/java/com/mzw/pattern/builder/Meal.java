package com.mzw.pattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class Meal {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public double getCost() {
        return items.stream().mapToDouble(Item::price).sum();
    }
    public void showItems() {
        items.forEach(item -> {
            System.out.println("Item :" + item.name());
            System.out.println(", Packing :" + item.packing().pack());
            System.out.println(", Price :" + item.price());
        });
    }
}
