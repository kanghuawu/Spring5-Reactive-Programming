package com.khwu.java8_in_action.ch05_work_with_stream;

import com.khwu.java8_in_action.ch04_intro_to_stream.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindMatching {

    public static void main(String[] args) {
        boolean isHealty = Dish.menu.stream()
                .anyMatch(d -> d.getCalories() < 1000);
        System.out.println(isHealty);

        isHealty = Dish.menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(isHealty);

        Optional<Dish> dish = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        dish.ifPresent(System.out::println);

        List<Integer> someNums = Arrays.asList(1, 2, 3, 4, 5);
        someNums.stream()
                .map(x -> x*x)
                .filter(x -> x%3 == 0)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
