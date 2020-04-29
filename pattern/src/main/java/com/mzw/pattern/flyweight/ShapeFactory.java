package com.mzw.pattern.flyweight;

import com.mzw.pattern.common.Circle;
import com.mzw.pattern.common.ColorType;
import com.mzw.pattern.common.Shape;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@Slf4j
public class ShapeFactory {
    private static final HashMap<ColorType, Shape> shpeMap = new HashMap<>();

    public static Shape getShape(ColorType colorType) {
        Shape shape;
        if (shpeMap.containsKey(colorType)) {
            shape = shpeMap.get(colorType);
        } else {
            shape = new Circle(colorType);
            shpeMap.put(colorType, shape);
            log.info("Creating circle of color : {}", colorType);
        }
        return shape;
    }
}
