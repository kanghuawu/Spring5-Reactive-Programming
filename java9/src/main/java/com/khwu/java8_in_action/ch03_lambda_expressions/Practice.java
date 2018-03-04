package com.khwu.java8_in_action.ch03_lambda_expressions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Practice {
    public static void main(String[] args) {
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        System.out.println(a1);

        BiFunction<Integer, String, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(110, "green");
        System.out.println(a2);

        BiFunction<Integer, String, Apple> c3 = (w, c) -> new Apple(w, c);
        Apple a3 = c3.apply(220, "blue");
        System.out.println(a3);

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(155, "blue"),
                new Apple(120, "red"));

        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(inventory);

        inventory.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
        System.out.println(inventory);

        Predicate<Apple> redApple = apple -> "red".equalsIgnoreCase(apple.getColor());
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
        Predicate<Apple> redAndHeavyOrGreenApple = redAndHeavyApple
                .or(apple -> "green".equalsIgnoreCase(apple.getColor()));


    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Apple {
        private int weight;
        private String color;
    }

    public static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return Integer.compare(o1.getWeight(), o2.getWeight());
        }
    }
}
