package java8_in_action.ch10.mkyoung.com;

import java.util.Optional;

public class OptionalMapFlaMapMain {
    public static void main(String[] args) {
        Optional<String> nonEmptyGeneder = Optional.of("male");
        Optional<String> emptyGender = Optional.empty();

        System.out.println(nonEmptyGeneder.map(String::toUpperCase));
        System.out.println(emptyGender.map(String::toUpperCase));

        Optional<Optional<String>> nonEmptyOptionalGender = Optional.of(Optional.of("male"));
        System.out.println(nonEmptyOptionalGender);
        System.out.println(nonEmptyOptionalGender.map(g -> g.map(String::toUpperCase)));
        System.out.println(nonEmptyOptionalGender.flatMap(g -> g.map(String::toUpperCase)));
    }
}
