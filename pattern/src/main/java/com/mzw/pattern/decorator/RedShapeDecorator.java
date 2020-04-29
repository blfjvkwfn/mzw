package com.mzw.pattern.decorator;

import com.mzw.pattern.common.Shape;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@Slf4j
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        super.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape) {
        log.info("Border color: red");
    }
}
