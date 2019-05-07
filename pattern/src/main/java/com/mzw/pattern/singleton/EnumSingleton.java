package com.mzw.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
@Slf4j
public enum  EnumSingleton {
    INSTANCE;
    public void printMessage() {
        log.info("EnumSingleton");
    }
}
