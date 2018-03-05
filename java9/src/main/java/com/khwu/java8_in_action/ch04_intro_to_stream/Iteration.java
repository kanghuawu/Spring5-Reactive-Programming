package com.khwu.java8_in_action.ch04_intro_to_stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Iteration {
    public static void main(String[] args) {
        System.out.println("External Iteration");
        List<String> names = new ArrayList<>();
        for (Dish d : Dish.menu) {
            names.add(d.getName());
        }
        System.out.println(names);

        System.out.println("External Iteration");
        names = new ArrayList<>();
        Iterator<Dish> iterator = Dish.menu.iterator();
        while (iterator.hasNext()) {
            names.add(iterator.next().getName());
        }
        System.out.println(names);

        System.out.println("Internal Iteration");
        names = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(names);
    }
}
