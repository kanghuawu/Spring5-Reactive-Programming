package com.khwu.java8_in_action.ch09.diamond_problem;

public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
