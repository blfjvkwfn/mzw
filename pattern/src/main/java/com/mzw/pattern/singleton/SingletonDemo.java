package com.mzw.pattern.singleton;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class SingletonDemo {
    public static void main(String[] args) {
        EagerSingleton.getInstance().printMessage();
        EnumSingleton.INSTANCE.printMessage();
        LazySingleton.getInstance().printMessage();
        SafeSingleton.getInstance().printMessage();
        StaticInnerClassSingleton.getInstance().printMessage();
    }
}
