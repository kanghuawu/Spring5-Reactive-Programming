package com.khwu.java8_in_action.ch10_optional.domain;

import java.util.Optional;

public class Car {
    private Insurance insurance;
    public Optional<Insurance> getInsurance() {
        return Optional.ofNullable(insurance);
    }
}
