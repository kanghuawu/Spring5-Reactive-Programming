package com.khwu.java8_in_action.ch09_default_methods.resolution_1;

public interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    } }
