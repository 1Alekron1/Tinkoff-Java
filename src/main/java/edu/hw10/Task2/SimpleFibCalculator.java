package edu.hw10.Task2;

public class SimpleFibCalculator implements FibCalculator {

    @Override
    public long fib(int number) {
        if (number <= 1) {
            return number;
        }

        long fib1 = 0;
        long fib2 = 1;
        long result = 0;

        for (int i = 2; i <= number; i++) {
            result = fib1 + fib2;
            fib1 = fib2;
            fib2 = result;
        }

        return result;
    }
}
