package java8_in_action.ch09.resolution_2;

public interface B {
    default void hello() {
        System.out.println("Hello from B");
    } }
