package java8_in_action.ch10.property;

import com.sun.tools.javac.main.Option;

import java.util.Optional;
import java.util.Properties;

public class PropertyMain {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");
        System.out.println(readDuration(props, "a"));
        System.out.println(readDuration(props, "d"));
    }
    public static int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalUtility::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }
}
