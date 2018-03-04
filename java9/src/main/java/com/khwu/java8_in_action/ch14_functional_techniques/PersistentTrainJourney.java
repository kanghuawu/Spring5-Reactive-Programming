package com.khwu.java8_in_action.ch14_functional_techniques;

import java.util.function.Consumer;

public class PersistentTrainJourney {
    public static void main(String[] args) {
        TrainJourney tj1 = new TrainJourney(40, new TrainJourney(30, null));
        TrainJourney tj2 = new TrainJourney(20, new TrainJourney(50, null));
        Consumer<TrainJourney> consumer = tj -> System.out.print(tj.price + " -");

        TrainJourney appended = append(tj1, tj2);
        visit(appended, consumer);
        System.out.println();
        visit(tj1, consumer);
        System.out.println();

        tj1 = new TrainJourney(40, new TrainJourney(30, null));
        tj2 = new TrainJourney(20, new TrainJourney(50, null));

        TrainJourney appended2 = append(tj1, tj2);
        visit(appended2, consumer);
        System.out.println();
        visit(tj1, consumer);
        System.out.println();

        tj1 = new TrainJourney(40, new TrainJourney(30, null));
        tj2 = new TrainJourney(20, new TrainJourney(50, null));

        TrainJourney linked = link(tj1, tj2);
        visit(linked, consumer);
        System.out.println();
        visit(tj1, consumer);
        System.out.println();
    }

    static class TrainJourney {
        public int price;
        public TrainJourney onward;

        public TrainJourney(int price, TrainJourney onward) {
            this.price = price;
            this.onward = onward;
        }
    }

    static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) return b;
        TrainJourney t = a;
        while (t.onward != null) t = t.onward;
        t.onward = b;
        return a;
    }

    static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

    static void visit(TrainJourney trainJourney, Consumer<TrainJourney> consumer) {
        if (trainJourney != null) {
            consumer.accept(trainJourney);
            visit(trainJourney.onward, consumer);
        }
    }
}
