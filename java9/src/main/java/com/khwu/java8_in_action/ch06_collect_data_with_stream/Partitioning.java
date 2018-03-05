package com.khwu.java8_in_action.ch06_collect_data_with_stream;

import com.khwu.java8_in_action.ch04_intro_to_stream.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Partitioning {

    public static void main(String[] args) {
        Map<Boolean, List<Dish>> partitionedMenu =
                Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);
        System.out.println(partitionedMenu.get(true));

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )));
        System.out.println(mostCaloricPartitionedByVegetarian);

        Map<Boolean, List<Integer>> partitionPrimes = IntStream.rangeClosed(2, 100).boxed()
                .collect(Collectors.partitioningBy(Partitioning::isPrime));
        System.out.println(partitionPrimes);
    }

    public static boolean isPrime(int candidate) {
        return IntStream.range(2, (int) Math.sqrt(candidate))
                .noneMatch(n -> candidate % n == 0);
    }
}
