package com.khwu.java8_in_action.ch09.resolution_2;

public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}