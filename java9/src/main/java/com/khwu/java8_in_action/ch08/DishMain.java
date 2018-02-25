package com.khwu.java8_in_action.ch08;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class DishMain {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    public enum Type { MEAT, FISH, OTHER }
    public enum CaloricLevel { DIET, NORMAL, FAT }

    public CaloricLevel getCaloricLevel() {
        if (this.getCalories() <= 400) return CaloricLevel.DIET;
        else if (this.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    }

    public static final List<DishMain> menu =
            Arrays.asList( new DishMain("pork", false, 800, DishMain.Type.MEAT),
                    new DishMain("beef", false, 700, DishMain.Type.MEAT),
                    new DishMain("chicken", false, 400, DishMain.Type.MEAT),
                    new DishMain("french fries", true, 530, DishMain.Type.OTHER),
                    new DishMain("rice", true, 350, DishMain.Type.OTHER),
                    new DishMain("season fruit", true, 120, DishMain.Type.OTHER),
                    new DishMain("pizza", true, 550, DishMain.Type.OTHER),
                    new DishMain("prawns", false, 400, DishMain.Type.FISH),
                    new DishMain("salmon", false, 450, DishMain.Type.FISH));
}
