package com.mzw.pattern.facade;

/**
 * 外观模式
 * @author Jonathan Meng
 * @date 08/05/2019
 */
public class Demo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
