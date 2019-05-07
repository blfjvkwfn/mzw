package com.mzw.pattern.factory;

import java.util.Objects;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class ShapeFactory extends AbstractFactory{
    @Override
    public Shape getShape(ShapeType shapeType) {
        if (Objects.isNull(shapeType)) {
            return null;
        }

        Shape shape = null;
        switch (shapeType) {
            case CIRCLE:
                shape = new Circle();
                break;
            case RECTANGLE:
                shape = new Rectangle();
                break;
            case SQUARE:
                shape = new Square();
                break;
            default:
                break;
        }

        return shape;
    }
}
