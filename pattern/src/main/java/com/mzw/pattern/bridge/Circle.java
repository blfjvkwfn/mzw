package com.mzw.pattern.bridge;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class Circle extends Shape {
    private int x, y, radius;

    public Circle(DrawApi drawApi, int x, int y, int radius) {
        super(drawApi);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawApi.drawCircle(radius, x, y);
    }
}
