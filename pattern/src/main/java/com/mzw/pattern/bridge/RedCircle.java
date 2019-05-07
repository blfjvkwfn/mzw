package com.mzw.pattern.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
@Slf4j
public class RedCircle implements DrawApi {
    @Override
    public void drawCircle(int radius, int x, int y) {
        log.info("Drawing Circle[ color: red, radius: {}, x: {}, y: {}]", radius, x, y);
    }
}
