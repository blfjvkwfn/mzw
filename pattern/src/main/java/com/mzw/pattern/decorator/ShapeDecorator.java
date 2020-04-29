package com.mzw.pattern.decorator;

import com.mzw.pattern.common.Shape;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@Data
@AllArgsConstructor
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;
    @Override
    public void draw() {
        decoratedShape.draw();
    }
}
