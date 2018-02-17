package java8_in_action.ch14;

import java.util.function.DoubleUnaryOperator;

public class CurryingMain {
    private static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }
    public static void main(String[] args) {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);
        System.out.printf("Converting %.0f Celsius to %.0f Fahrenheit%n", 100.0, convertCtoF.applyAsDouble(100));
        System.out.printf("Converting %.0f USD to %.0f GBP%n", 1000.0, convertUSDtoGBP.applyAsDouble(1000));
        System.out.printf("Converting %.1f Km to %.1f Mi%n", 7.0, convertKmtoMi.applyAsDouble(7));
    }
}
