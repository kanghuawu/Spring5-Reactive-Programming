package java8_in_action.ch10.domain;

import java.util.Optional;

public class Car {
    private Insurance insurance;
    public Optional<Insurance> getInsurance() {
        return Optional.ofNullable(insurance);
    }
}
