package com.mzw.pattern.common;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
@Slf4j
public class Square implements Shape {
    @Override
    public void draw() {
        log.info("Inside Square draw.");
    }
}
