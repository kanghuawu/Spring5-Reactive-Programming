package com.khwu.java8_in_action.ch14_functional_techniques;

import java.util.function.Function;

public class Combinators {
    public static void main(String[] args) {
        System.out.println(repeat(3, (Integer x) -> 2 * x).apply(1));
    }

    private static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    private static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n-1, f));
    }
}
