package com.khwu.java8_in_action.ch09_default_methods.diamond_problem;

public class D implements B, C {
    public static void main(String[] args) {
        new D().hello();
    }
}
