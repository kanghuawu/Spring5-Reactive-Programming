package com.khwu.java8_in_action.ch12_new_date_time;

import java.time.*;
import java.time.temporal.ChronoField;

public class NewDateTimeAPI {

    public static void main(String[] args) {
        System.out.println("=== Date ===");
        LocalDate date = LocalDate.of(2014, 3, 18);
        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfWeek());
        System.out.println(date.lengthOfMonth());
        System.out.println(date.isLeapYear());

        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_MONTH));

        System.out.println("=== Time ===");
        LocalTime time = LocalTime.of(13, 45, 20);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());

        System.out.println("=== Parse ===");
        date = LocalDate.parse("2014-03-18");
        time = LocalTime.parse("13:45:20");
        System.out.println(date.getYear());
        System.out.println(time.getSecond());

        System.out.println("=== DateTime ===");
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        System.out.println(dt1);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        System.out.println(dt2);
        LocalDateTime dt3 = date.atTime(time);
        System.out.println(dt3);
        LocalDateTime dt4 = time.atDate(date);
        System.out.println(dt4);

        System.out.println("=== Period and Duration ===");
        Duration d1 = Duration.between(LocalTime.of(13, 45), LocalTime.of(13, 00));
        System.out.println(d1);
        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));
        System.out.println(tenDays);
    }
}
