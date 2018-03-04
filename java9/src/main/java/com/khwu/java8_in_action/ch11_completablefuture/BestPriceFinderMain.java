package com.khwu.java8_in_action.ch11_completablefuture;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {
    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();
    public static void main(String[] args) {
        execute("sequential", () -> bestPriceFinder.findPrices("myPhone27s"));
        execute("future", () -> bestPriceFinder.findPriceFutures("myPhone27s"));
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27s"));
        execute("quote/discount", () -> bestPriceFinder.findPriceWithQuotesDiscount("myPhone27s"));
        execute("quote/discount and future", () -> bestPriceFinder.findPriceWithQuoteDiscountFuture("myPhone27s"));
        execute("exchange rate and future", () -> bestPriceFinder.findPriceInUSD("myPhone27s"));
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("%s done in %d msecs%n", msg, duration);
    }
}
