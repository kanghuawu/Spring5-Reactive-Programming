package com.khwu.java8_in_action.ch14;

import java.util.function.Function;
import java.util.function.Supplier;

public class PatternMatching {

    public static void main(String[] args) {
        Expr e = new BinOp("+", new Number(5), new Number(0));
        Expr match = simplify(e);
        System.out.println(e + " = " + match);

        Expr e2 = new BinOp("+", new Number(5), new BinOp("*", new Number(3), new Number(4)));
        Integer result = evaluate(e2);
        System.out.println(e2 + " = " + result);
    }

    public static Integer evaluate(Expr e) {
        Function<Integer, Integer> numCase = val -> val;
        Supplier<Integer> defaultCase = () -> 0;
        TriFunction<String, Expr, Expr, Integer> binOpCase = (opName, left, right) -> {
            if ("+".equals(opName)) {
                if (left instanceof Number && right instanceof Number) {
                    return ((Number) left).val + ((Number) right).val;
                } else if (left instanceof Number && right instanceof BinOp) {
                    return ((Number) left).val + evaluate(right);
                } else if (left instanceof BinOp && right instanceof Number) {
                    return evaluate(left) + ((Number) right).val;
                } else if (left instanceof BinOp && right instanceof BinOp) {
                    return evaluate(left) + evaluate(right);
                }
            } else if ("*".equals(opName)) {
                if (left instanceof Number && right instanceof Number) {
                    return ((Number) left).val * ((Number) right).val;
                } else if (left instanceof Number && right instanceof BinOp) {
                    return ((Number) left).val * evaluate(right);
                } else if (left instanceof BinOp && right instanceof Number) {
                    return evaluate(left) * ((Number) right).val;
                } else if (left instanceof BinOp && right instanceof BinOp) {
                    return evaluate(left) * evaluate(right);
                }
            }
            return defaultCase.get();
        };

        return patternMatchExpr(e, binOpCase, numCase, defaultCase);
    }

    public static Expr simplify(Expr e) {
        TriFunction<String, Expr, Expr, Expr> binOpCase = (opName, left, right) -> {
            if ("+".equals(opName)) {
                if (left instanceof Number && ((Number) left).val == 0) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).val == 0) {
                    return left;
                }
            }
            if ("*".equals(opName)) {
                if (left instanceof Number && ((Number) left).val == 1) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).val == 1) {
                    return left;
                }
            }
            return new BinOp(opName, left, right);
        };
        Function<Integer, Expr> numCase = Number::new;
        Supplier<Expr> defaultCase = () -> new Number(0);

        return patternMatchExpr(e, binOpCase, numCase, defaultCase);
    }

    interface TriFunction<S, T, U, R> {
        R apply(S s, T t, U u);
    }

    static <T> T patternMatchExpr (Expr e, TriFunction<String, Expr, Expr, T> binOpCase,
                                   Function<Integer, T> numCase, Supplier<T> defaultCase) {
        if (e instanceof BinOp) {
            return binOpCase.apply(((BinOp) e).opName, ((BinOp) e).left, ((BinOp) e).right);
        } else if (e instanceof Number) {
            return numCase.apply(((Number) e).val);
        } else {
            return defaultCase.get();
        }
    }

    static class Expr {}

    static class Number extends Expr {
        int val;

        public Number(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    static class BinOp extends Expr {
        String opName;
        Expr left, right;

        public BinOp(String opName, Expr left, Expr right) {
            this.opName = opName;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("(%s %s %s)", left, opName, right);
        }
    }

    static <T> T myIf(boolean b, Supplier<T> trueCase, Supplier<T> falseCase) {
        return b ? trueCase.get() : falseCase.get();
    }
}
