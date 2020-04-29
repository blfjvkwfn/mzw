package com.mzw.pattern.bridge;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedCircle(),100,100,10);
        Shape greenCircle = new Circle(new GreenCircle(),100,100,10);

        redCircle.draw();
        greenCircle.draw();
    }
}
