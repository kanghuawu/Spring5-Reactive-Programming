package com.khwu.java8_in_action.ch04_intro_to_stream;

import java.util.List;
import java.util.stream.Collectors;

public class Operations {
    public static void main(String[] args) {
        List<String> names = Dish.menu.stream()
                .filter(dish -> {
                    System.out.println("filtering " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);
    }
}
