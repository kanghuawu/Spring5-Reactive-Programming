package com.khwu.java8_in_action.ch05_work_with_stream;

import com.khwu.java8_in_action.ch04_intro_to_stream.Dish;

import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Numberic {

    public static void main(String[] args) {
        int cal = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(cal);

        Stream<Integer> stream = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .boxed();
        stream.forEach(n -> System.out.print(n + " "));
        System.out.println();

        OptionalInt maxNum = new ArrayList<String>().stream()
                .mapToInt(Integer::parseInt)
                .max();
        System.out.println(maxNum.orElse(10));

        IntStream evenNum = IntStream.rangeClosed(1, 100)
                .filter(n -> n%2 == 0);
        System.out.println(evenNum.count());

        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                    .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                    .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
                .forEach(n -> System.out.print("(" + n[0] + ", " +
                        n[1] + ", " + n[2] + ") "));
    }
}
