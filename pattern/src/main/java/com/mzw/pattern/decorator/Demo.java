package com.mzw.pattern.decorator;

import com.mzw.pattern.common.Circle;
import com.mzw.pattern.common.Rectangle;
import com.mzw.pattern.common.Shape;
import lombok.extern.slf4j.Slf4j;

/**
 * 装饰者模式
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@Slf4j
public class Demo {
    public static void main(String[] args) {
        Shape circle = new Circle();

        Shape redCircle = new RedShapeDecorator(new Circle());

        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        log.info("Circle with normal border");
        circle.draw();

        log.info("Circle of red border");
        redCircle.draw();

        log.info("Rectangle of red border");
        redRectangle.draw();
    }
}
