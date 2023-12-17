package edu.hw10.Task2;

public class FactorialCalculator implements Calculator {

    @Override
    public int calculate(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Input should be a non-negative number");
        }

        if (number == 0 || number == 1) {
            return 1;
        }

        return number * calculate(number - 1);
    }
}
