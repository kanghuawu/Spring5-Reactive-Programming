package com.khwu.java8_in_action.ch03_lambda_expressions;

public class FunctionalInterface {
    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Runnable 1");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable 2");
            }
        };
        process(r1);
        process(r2);
        process(() -> System.out.println("Runnable 3"));
    }

    public static void process(Runnable r) {
        r.run();
    }
}
