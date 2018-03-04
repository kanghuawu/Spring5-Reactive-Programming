package com.khwu.java8_in_action.ch08_refactor_test_debug;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Main {
    public static void main(String[] args) {
        Map<DishMain.CaloricLevel, List<DishMain>> dishesByCaloricLevel = DishMain.menu.stream()
                .collect(groupingBy(DishMain::getCaloricLevel));
        System.out.println(dishesByCaloricLevel);
        int totalCalories = DishMain.menu.stream()
                .map(DishMain::getCalories)
                .reduce(0, (d1, d2) -> d1 + d2);
        System.out.println(totalCalories);
        totalCalories = DishMain.menu.stream()
                .collect(summingInt(DishMain::getCalories));
        System.out.println(totalCalories);


    }
}
