package com.mzw.pattern.builder;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("veg meal");
        vegMeal.showItems();
        System.out.println("Total cost:" + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNoneVegMeal();
        System.out.println("\nNon-Veg meal");
        nonVegMeal.showItems();
        System.out.println("Total cost:" + nonVegMeal.getCost());
    }
}
