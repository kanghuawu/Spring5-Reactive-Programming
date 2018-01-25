package java8_in_action.ch08;

public class Validator {
    private final ValidationStrategy strategy;
    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
