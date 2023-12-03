package edu.hw7;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Task2 {

    private Task2() {
    }

    public static BigInteger calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Факториал определен только для неотрицательных чисел");
        }

        return LongStream.rangeClosed(1, number)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
