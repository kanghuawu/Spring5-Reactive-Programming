package java8_in_action.ch08;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class Dish {
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

    public static final List<Dish> menu =
            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                    new Dish("beef", false, 700, Dish.Type.MEAT),
                    new Dish("chicken", false, 400, Dish.Type.MEAT),
                    new Dish("french fries", true, 530, Dish.Type.OTHER),
                    new Dish("rice", true, 350, Dish.Type.OTHER),
                    new Dish("season fruit", true, 120, Dish.Type.OTHER),
                    new Dish("pizza", true, 550, Dish.Type.OTHER),
                    new Dish("prawns", false, 400, Dish.Type.FISH),
                    new Dish("salmon", false, 450, Dish.Type.FISH));
}
