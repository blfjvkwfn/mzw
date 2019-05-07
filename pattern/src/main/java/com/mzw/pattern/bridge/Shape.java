package com.mzw.pattern.bridge;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public abstract class Shape {
    protected DrawApi drawApi;

    public Shape(DrawApi drawApi) {
        this.drawApi = drawApi;
    }

    public abstract void draw();
}
