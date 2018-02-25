package com.khwu.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        System.out.println("Find all transactions in the year 2011 and sort them by value (small to high).");
        transactions.stream()
                .filter(txn -> txn.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getYear))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("What are all the unique cities where the traders work?");
        transactions.stream()
                .map(txn -> txn.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Find all traders from Cambridge and sort them by name.");
        transactions.stream()
                .filter(txn -> txn.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Return a string of all traders’ names sorted alphabetically");
        String traders = transactions.stream()
                .map(txn -> txn.getTrader().getName())
                .distinct()
                .sorted()
//                .reduce("", (trader1, trader2) -> trader1 + trader2);
                .collect(joining());
        System.out.println(traders);

        System.out.println();
        System.out.println("Are any traders based in Milan?");
        transactions.stream()
                .filter(txn -> txn.getTrader().getCity().equals("Milan"))
                .map(Transaction::getTrader)
                .distinct()
                .forEach(System.out::println);
        boolean inMilan = transactions.stream()
                .anyMatch(txn -> txn.getTrader().getCity().equals("Milan"));
        System.out.println(inMilan);

        System.out.println();
        System.out.println("Print all transactions’ values from the traders living in Cambridge.");
        transactions.stream()
                .filter(txn -> txn.getTrader().getCity().equals("Cambridge"))
                .forEach(txn -> System.out.println(txn.getValue()));

        System.out.println();
        System.out.println("What’s the highest value of all the transactions?");
        Transaction maxTxn = transactions.stream()
                .max(Comparator.comparing(Transaction::getValue))
                .get();
        System.out.println(maxTxn.getValue());

        System.out.println();
        System.out.println("Find the transaction with the smallest value.");
        Transaction minTxn = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .get();
        System.out.println(minTxn);
    }
}
