package com.khwu.java8_in_action.ch14;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class LazyStream {

    public static void main(String[] args) {
        MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));

        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two + " " + three + " " + four);

        numbers = from(2);
        int prime_two = primes(numbers).head();
        int prime_three = primes(numbers).tail().head();
        int prime_five = primes(numbers).tail().tail().head();
        System.out.println(prime_two + " " + prime_three + " " + prime_five);

        printAll(primes(from(2)));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(),
                () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n+1));
    }

    interface MyList<T> {
        T head();
        MyList<T> tail();
        default boolean isEmpty() {
            return true;
        }
        default MyList<T> filter(Predicate<T> p) {
            return isEmpty() ? this : p.test(head()) ?
                    new LazyList<>(head(), () -> tail().filter(p)) : tail().filter(p);
        }
    }

    static class LazyList<T> implements MyList<T> {
        private T head;
        private Supplier<MyList<T>> tail;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail.get();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    static class MyLinkedList<T> implements MyList<T> {
        private final T head;
        private final MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    static class Empty<T> implements MyList<T> {
        @Override
        public T head() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> filter(Predicate<T> p) {
            return this;
        }
    }

    private static <T> void printAll(MyList<T> list) {
        while (!list.isEmpty()) {
            System.out.println(list.head());
            list = list.tail();
        }
    }
}
