package com.khwu.java8_in_action.ch10.mkyoung.com;

import java.util.Optional;

public class OptionalConditionMain {
    public static void main(String[] args) {
        Optional<String> gender = Optional.of("MALE");
        Optional<String> emptyGender = Optional.empty();

        gender.ifPresent(g -> System.out.println("printing for gender"));
        emptyGender.ifPresent(g -> System.out.println("printing for emptyGender"));

        System.out.println(gender.orElse("FEMALE"));
        System.out.println(emptyGender.orElse("FEMALE"));

        System.out.println(gender.orElseGet(() -> "FEMALE"));
        System.out.println(emptyGender.orElseGet(() -> "FEMALE"));
    }
}
