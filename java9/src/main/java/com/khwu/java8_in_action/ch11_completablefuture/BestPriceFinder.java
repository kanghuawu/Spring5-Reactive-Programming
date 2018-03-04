package com.khwu.java8_in_action.ch11_completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

import com.khwu.java8_in_action.ch11_completablefuture.ExchangeService.Money;

public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                                                    new Shop("LetsSaveBig"),
                                                    new Shop("MyFavoriteShop"),
                                                    new Shop("BuyItAll"),
                                                    new Shop("BuyEasy"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });
    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public List<String> findPriceFutures(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor))
                .collect(toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

    public List<String> findPriceWithQuotesDiscount(String product) {
        return shops.stream()
                .map(shop -> shop.getPriceWithDiscount(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    public List<String> findPriceWithQuoteDiscountFuture(String product) {
         List<CompletableFuture<String>> prices = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscount(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                        () -> Discount.applyDiscount(quote), executor)))
                .collect(toList());
        return prices.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public List<String> findPriceInUSD(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture
                    .supplyAsync(() -> shop.getPrice(product), executor)
                    .thenCombine(
                            CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD), executor),
                            (price, rate) -> price * rate)
                    .thenApply(price -> String.format("%s price is %.2f", shop.getName(), price)))
                .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
}
