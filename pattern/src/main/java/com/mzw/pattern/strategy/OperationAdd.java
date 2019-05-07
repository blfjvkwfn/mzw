package com.mzw.pattern.strategy;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
