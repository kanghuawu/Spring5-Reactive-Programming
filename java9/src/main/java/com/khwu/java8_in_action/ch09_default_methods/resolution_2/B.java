package com.khwu.java8_in_action.ch09_default_methods.resolution_2;

public interface B {
    default void hello() {
        System.out.println("Hello from B");
    } }
