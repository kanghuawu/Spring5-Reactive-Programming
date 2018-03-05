package com.khwu.java8_in_action.ch05_work_with_stream;

import com.khwu.java8_in_action.ch04_intro_to_stream.Dish;

import java.util.Arrays;
import java.util.List;

public class Reducing {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        int sum = nums.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        int prod = nums.stream().reduce(1, (a, b) -> a*b);
        System.out.println(prod);

        nums.stream().reduce(Integer::max).ifPresent(System.out::println);

        nums.stream().reduce(Integer::min).ifPresent(System.out::println);

        int count = Dish.menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        System.out.println(count);

        long count2 = Dish.menu.stream().count();
        System.out.println(count2);
    }
}
