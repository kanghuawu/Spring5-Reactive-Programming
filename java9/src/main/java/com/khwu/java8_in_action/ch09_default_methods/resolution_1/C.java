package com.khwu.java8_in_action.ch09_default_methods.resolution_1;

public class C extends D implements B, A {
    public static void main(String... args) {
        new C().hello();
    }
}