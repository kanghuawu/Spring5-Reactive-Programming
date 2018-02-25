package com.khwu.java8_in_action.ch10.mkyoung.com;

import java.util.Optional;

public class OptionalBasicMain {
    public static void main(String[] args) {
        Optional<String> gender = Optional.of("MALE");
        String ans1 = "Yes";
        String ans2 = null;

        System.out.println(gender);
        System.out.println(gender.get());
        System.out.println(Optional.empty());

        System.out.println(Optional.ofNullable(ans1));
        System.out.println(Optional.ofNullable(ans2));

        System.out.println(Optional.of(ans1));
//        System.out.println(Optional.of(ans2));
    }
}
