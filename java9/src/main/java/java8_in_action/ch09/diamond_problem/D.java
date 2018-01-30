package java8_in_action.ch09.diamond_problem;

public class D implements B, C {
    public static void main(String[] args) {
        new D().hello();
    }
}
