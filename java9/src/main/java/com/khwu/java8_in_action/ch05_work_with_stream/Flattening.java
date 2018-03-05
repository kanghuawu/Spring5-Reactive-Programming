package com.khwu.java8_in_action.ch05_work_with_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Flattening {
    public static void main(String[] args) {
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        List<String> words = streamOfwords
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(words);

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = nums.stream()
                .map(n -> n*n)
                .collect(Collectors.toList());
        System.out.println(squares);

        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);
        List<int[]> pairs = num1.stream()
                .flatMap(i -> num2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.forEach(p -> System.out.print(Arrays.toString(p) + " "));
        System.out.println();

        List<int[]> pairsDivisibleBy3 = num1.stream()
                .flatMap(i -> num2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairsDivisibleBy3.forEach(p -> System.out.print(Arrays.toString(p) + " "));
        System.out.println();
    }
}
