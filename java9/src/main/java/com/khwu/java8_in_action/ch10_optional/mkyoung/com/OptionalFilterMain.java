package com.khwu.java8_in_action.ch10_optional.mkyoung.com;

import java.util.Optional;

public class OptionalFilterMain {
    public static void main(String[] args) {
        Optional<String> gender = Optional.of("MALE");
        Optional<String> emptyGender = Optional.empty();

        System.out.println(gender.filter(g -> g.equals("male")));
        System.out.println(gender.filter(g -> g.equalsIgnoreCase("male")));
        System.out.println(emptyGender.filter(g -> g.equalsIgnoreCase("male")));
    }
}
