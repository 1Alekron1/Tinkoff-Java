package edu.hw1;

import java.util.Arrays;
import java.util.Collections;

public class Task6 {
    final static int DIGITS_COUNT = 4;
    final static int MAXIMUM_VALUE = 10000;
    final static int MINIMUM_VALUE = 1000;
    final static int FINAL_VALUE = 6174;
    final static int DIVIDER = 10;

    public static int countK(int number) {
        if (number == FINAL_VALUE || number >= MAXIMUM_VALUE || number <= MINIMUM_VALUE) {
            return 0;
        }

        int ascending = getSortedNumber(number, true);
        int descending = getSortedNumber(number, false);

        int diff = descending - ascending;

        if (diff <= MINIMUM_VALUE) {
            return 0;
        } else {
            return 1 + countK(diff);
        }
    }

    public static int getSortedNumber(int number, boolean ascending) {
        var digits = new Integer[DIGITS_COUNT];
        var tempNumber = number;
        for (int i = 0; i < DIGITS_COUNT; i++) {
            digits[i] = tempNumber % DIVIDER;
            tempNumber /= DIVIDER;
        }

        if (ascending) {
            Arrays.sort(digits);
        } else {
            Arrays.sort(digits, Collections.reverseOrder());
        }

        int result = 0;
        for (int i = 0; i < DIGITS_COUNT; i++) {
            result = result * DIVIDER + digits[i];
        }

        return result;
    }

    private Task6() {
    }
}
