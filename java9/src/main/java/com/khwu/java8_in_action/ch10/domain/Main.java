package com.khwu.java8_in_action.ch10.domain;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<Person> optPerson = Optional.ofNullable(new Person());
        System.out.println(getCarInsuranceName(optPerson));
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
