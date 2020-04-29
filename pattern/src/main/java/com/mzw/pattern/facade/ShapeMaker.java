package com.mzw.pattern.facade;

import com.mzw.pattern.common.Circle;
import com.mzw.pattern.common.Rectangle;
import com.mzw.pattern.common.Shape;
import com.mzw.pattern.common.Square;
import lombok.Data;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@Data
public class ShapeMaker {
    private Shape circle = new Circle();
    private Shape rectangle = new Rectangle();
    private Shape square = new Square();

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}
