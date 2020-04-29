package com.mzw.pattern.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@Slf4j
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Circle implements Shape {
    @NonNull
    private ColorType colorType;
    private int x;
    private int y;
    private int radius;
    @Override
    public void draw() {
        log.info("Shape: Circle: " + toString());
    }
}
