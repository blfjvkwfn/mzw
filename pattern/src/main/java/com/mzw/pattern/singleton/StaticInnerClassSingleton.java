package com.mzw.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
@Slf4j
public class StaticInnerClassSingleton implements Singleton{
    private StaticInnerClassSingleton() {
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void printMessage() {
        log.info("StaticInnerClassSingleton");
    }
}
