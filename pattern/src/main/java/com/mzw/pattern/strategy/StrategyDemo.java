package com.mzw.pattern.strategy;

/**
 * @author Jonathan Meng
 * @date 06/05/2019
 */
public class StrategyDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());

        System.out.println("10 + 5 = " + context.execute(10, 5));

        context.setStrategy(new OperationSubstract());
        System.out.println("10 - 5 = " + context.execute(10, 5));

        context.setStrategy(new OperationMultiply());
        System.out.println("10 * 5 = " + context.execute(10, 5));
    }
}
