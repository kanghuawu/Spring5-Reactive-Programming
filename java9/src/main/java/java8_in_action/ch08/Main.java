package java8_in_action.ch08;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java8_in_action.ch08.Dish.CaloricLevel;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java8_in_action.ch08.Dish.menu;

public class Main {
    public static void main(String[] args) {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(groupingBy(Dish::getCaloricLevel));
        System.out.println(dishesByCaloricLevel);
        int totalCalories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (d1, d2) -> d1 + d2);
        System.out.println(totalCalories);
        totalCalories = menu.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);


    }
}
