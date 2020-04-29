package com.mzw.pattern.flyweight;

import com.mzw.pattern.common.Circle;
import com.mzw.pattern.common.ColorType;

import java.util.Random;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
public class Demo {
    public static void main(String[] args) {
        ColorType[] colorTypes = {ColorType.RED, ColorType.BLACK, ColorType.BULE, ColorType.GREEN, ColorType.WHITE};
        Random index = new Random(colorTypes.length - 1);
        Random x = new Random(100);
        Random y = new Random(100);
        for (int i = 0; i < 20; i++) {
            Circle shape = (Circle) ShapeFactory.getShape(colorTypes[index.nextInt(5)]);
            shape.setX(x.nextInt());
            shape.setY(y.nextInt());
            shape.draw();
        }
    }
}
