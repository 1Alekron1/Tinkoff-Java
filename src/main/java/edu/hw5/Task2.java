package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static final int MONTH_IN_A_YEAR = 12;
    public static final int DAY_OF_MONTH = 13;

    private Task2() {
    }

    public static List<LocalDate> findFridayThe13ths(int year) {
        List<LocalDate> fridayThe13ths = new ArrayList<>();

        for (int month = 1; month <= MONTH_IN_A_YEAR; month++) {
            LocalDate date = LocalDate.of(year, month, DAY_OF_MONTH);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridayThe13ths.add(date);
            }
        }

        return fridayThe13ths;
    }

    public static LocalDate findNextFridayThe13th(LocalDate currentDate) {
        LocalDate nextFridayThe13th = currentDate;

        do {
            nextFridayThe13th = nextFridayThe13th.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        } while (nextFridayThe13th.getDayOfMonth() != DAY_OF_MONTH);

        return nextFridayThe13th;
    }
}
