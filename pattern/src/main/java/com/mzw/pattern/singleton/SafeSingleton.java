package com.mzw.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
@Slf4j
public class SafeSingleton implements Singleton{
    private static Singleton instance;

    private SafeSingleton() {
    }

    public static Singleton getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (SafeSingleton.class) {
                if (Objects.isNull(instance)) {
                    instance = new SafeSingleton();
                }
            }
        }

        return instance;
    }

    @Override
    public void printMessage() {
        log.info("SafeSingleton");
    }
}
