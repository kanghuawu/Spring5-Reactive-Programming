package com.khwu.java8_in_action.ch08;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Point  {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Point that = (Point) obj;
        return this.x == that.x && this.y == that.y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public Point moveRightBy(int x){
        return new Point(this.x + x, this.y);
    }

    public final static Comparator<Point> compareByXAndThenY = comparing(Point::getX)
            .thenComparing(Point::getY);

    public static List<Point> moveAllPointsRightBy(List<Point> points, int delta) {
        return points.stream().map(p -> p.moveRightBy(delta))
                .collect(toList());
    }
}
