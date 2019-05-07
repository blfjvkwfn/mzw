package com.mzw.pattern.factory;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public abstract class AbstractFactory {
    abstract Shape getShape(ShapeType shapeType);
}
