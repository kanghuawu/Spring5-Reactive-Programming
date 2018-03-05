package com.khwu.java8_in_action.ch06_collect_data_with_stream;

import com.khwu.java8_in_action.ch04_intro_to_stream.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class Summarizing {

    public static void main(String[] args) {
        long hoManyDishes = Dish.menu.stream().count();
        System.out.println(hoManyDishes);

        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = Dish.menu.stream()
                .collect(Collectors.maxBy(dishCaloriesComparator));
        mostCaloriesDish.ifPresent(System.out::println);

        int totalCalories = Dish.menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        double avgCalories = Dish.menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        IntSummaryStatistics menuStatistics = Dish.menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        String shortMenu = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        System.out.println(shortMenu);

        totalCalories = Dish.menu.stream()
                .collect(Collectors.reducing(
                        0, Dish::getCalories, (i, j) -> i + j
                ));
        System.out.println(totalCalories);

        shortMenu = Dish.menu.stream()
                .collect(Collectors.reducing("", Dish::getName, (i, j) -> i + j + ", "));
        System.out.println(shortMenu);
    }
}
