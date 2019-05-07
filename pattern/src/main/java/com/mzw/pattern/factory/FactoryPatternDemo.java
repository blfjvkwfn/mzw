package com.mzw.pattern.factory;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        factory.getShape(ShapeType.CIRCLE).draw();

        factory.getShape(ShapeType.RECTANGLE).draw();

        factory.getShape(ShapeType.SQUARE).draw();

        //abstract factory
        AbstractFactory abstractFactory = FactoryProducer.getFactory(FactoryType.SHAPE);
        abstractFactory.getShape(ShapeType.CIRCLE).draw();
    }
}
