package java8_in_action.ch10.domain;

import java.util.Optional;

public class Person {
    private Car car;
    public Optional<Car> getCar() { return Optional.ofNullable(car); }

}
