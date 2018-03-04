package com.khwu.java8_in_action.ch08_refactor_test_debug;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FactoryMain {
    static interface Product {}
    static class Loan implements Product {
        @Override
        public String toString() {
            return "This is a loan";
        }
    }
    static class Stock implements Product {
        @Override
        public String toString() {
            return "This is a stock";
        }
    }
    static class Bond implements Product {
        @Override
        public String toString() {
            return "This is a bond";
        }
    }

    static class ProductFactory {
        static Product createProduct(String name) {
            switch (name) {
                case "loan": return new Loan();
                case "stock": return new Stock();
                case "bond": return new Bond();
                default: throw new IllegalArgumentException();
            }
        }

        static Supplier<Product> createProductLambda(String name) {
            Supplier<Product> re = map.get(name);
            if (re != null) return re;
            throw new IllegalArgumentException();
        }
    }

    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static void main(String[] args) {
        Product loan1 = ProductFactory.createProduct("loan");
        System.out.println(loan1);

        Supplier<Product> loanSupplier = Loan::new;
        Product loan2 = loanSupplier.get();
        System.out.println(loan2);

        Supplier<Product> loan3 = ProductFactory.createProductLambda("loan");
        System.out.println(loan3.get());
        System.out.println(loan3.hashCode());
    }
}
