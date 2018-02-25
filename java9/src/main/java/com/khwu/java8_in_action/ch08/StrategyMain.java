package com.khwu.java8_in_action.ch08;

public class StrategyMain {
    public static void main(String[] args) {
        Validator numericValidator = new Validator(s -> s.matches("\\d+"));
        Validator lowerCastValidator = new Validator(s -> s.matches("[a-z]+"));
        System.out.println(numericValidator.validate("123"));
        System.out.println(lowerCastValidator.validate("abc"));
    }
    interface ValidationStrategy {
        boolean execute(String s);
    }

    static class Validator {
        private final ValidationStrategy strategy;
        public Validator(ValidationStrategy strategy) {
            this.strategy = strategy;
        }

        public boolean validate(String s) {
            return strategy.execute(s);
        }
    }
}
