package com.mzw.pattern.factory;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public enum ShapeType {
    CIRCLE("CIRCLE"),RECTANGLE("RACTANGLE"),SQUARE("SQUARE");
    private String name;

    ShapeType(String name) {
        this.name = name;
    }
}
