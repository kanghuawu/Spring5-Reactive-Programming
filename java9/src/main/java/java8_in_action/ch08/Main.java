package java8_in_action.ch08;

import java.util.List;
import java.util.Map;
import java8_in_action.ch08.DishMain.CaloricLevel;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java8_in_action.ch08.DishMain.menu;

public class Main {
    public static void main(String[] args) {
        Map<CaloricLevel, List<DishMain>> dishesByCaloricLevel = menu.stream()
                .collect(groupingBy(DishMain::getCaloricLevel));
        System.out.println(dishesByCaloricLevel);
        int totalCalories = menu.stream()
                .map(DishMain::getCalories)
                .reduce(0, (d1, d2) -> d1 + d2);
        System.out.println(totalCalories);
        totalCalories = menu.stream()
                .collect(summingInt(DishMain::getCalories));
        System.out.println(totalCalories);


    }
}
