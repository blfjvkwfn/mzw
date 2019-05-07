package com.mzw.pattern.factory;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(FactoryType factoryType) {
        switch (factoryType) {
            case SHAPE:
                return new ShapeFactory();
        }

        return null;
    }
}
