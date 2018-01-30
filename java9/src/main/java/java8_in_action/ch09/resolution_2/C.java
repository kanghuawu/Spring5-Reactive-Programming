package java8_in_action.ch09.resolution_2;


public class C implements B, A {
    @Override
    public void hello() {
        B.super.hello();
    }

    public static void main(String... args) {
        new C().hello();
    }
}