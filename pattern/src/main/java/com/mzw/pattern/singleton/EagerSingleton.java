package com.mzw.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * eager singleton thread unsafe
 * @author Jonathan Meng
 * @date 06/05/2019
 */
@Slf4j
public class EagerSingleton implements Singleton{
    private static final EagerSingleton instance = new EagerSingleton();
    private EagerSingleton() {

    }

    public static Singleton getInstance() {
        return instance;
    }

    @Override
    public void printMessage() {
        log.info("EagerSingleton");
    }
}
