package edu.hw1;

public class Task2 {
    final static int DIVIDER = 10;

    public static int countDigits(Integer number) {
        var currentNumber = number;
        var counter = 0;
        do {
            currentNumber /= DIVIDER;
            counter++;
        }
        while (currentNumber != 0);

        return counter;
    }

    private Task2() {
    }
}
