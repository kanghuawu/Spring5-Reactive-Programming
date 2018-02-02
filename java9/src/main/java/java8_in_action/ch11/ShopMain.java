package java8_in_action.ch11;

import java.util.concurrent.Future;

public class ShopMain {
    public static void main(String[] args) {
        Shop shop = new Shop("CoolShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsyncSupply("CoolProduct");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.printf("Invocaton returned after %d msecs%n", invocationTime);

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() -  start) / 1_000_000);
        System.out.printf("Price returned after %d msecs%n", retrievalTime);
    }
}
