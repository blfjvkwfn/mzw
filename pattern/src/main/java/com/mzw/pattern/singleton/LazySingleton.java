package com.mzw.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * lazy singleton thread unsafe
 * @author Jonathan Meng
 * @date 06/05/2019
 */
@Slf4j
public class LazySingleton implements Singleton{
    private static LazySingleton instance;

    public static Singleton getInstance() {
        if (Objects.isNull(instance)) {
            instance = new LazySingleton();
        }
        return instance;
    }

    private LazySingleton() {
    }

    @Override
    public void printMessage() {
        log.info("LazySingleton");
    }
}
