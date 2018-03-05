package com.khwu.java8_in_action.ch06_collect_data_with_stream;

import com.khwu.java8_in_action.ch04_intro_to_stream.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Grouping {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String[] args) {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream()
                .collect(Collectors.groupingBy(Grouping::getCaloricLevel));
        System.out.println(dishesByCaloricLevel);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(Grouping::getCaloricLevel)));
        System.out.println(dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> typesCount = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByTypeGet = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get))); // reducing collector will never return an Optional.empty()
        System.out.println(mostCaloricByTypeGet);
    }

    private static CaloricLevel getCaloricLevel(Dish dish) {
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        return CaloricLevel.FAT;
    }
}
