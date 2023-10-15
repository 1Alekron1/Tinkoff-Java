package edu.hw1;

import java.util.Arrays;

public class Task3 {
    public static boolean isNestable(int[] array1, int[] array2) {
        return Arrays.stream(array1).min().orElse(Integer.MAX_VALUE)
            > Arrays.stream(array2).min().orElse(Integer.MAX_VALUE)
            && Arrays.stream(array1).max().orElse(Integer.MIN_VALUE)
            < Arrays.stream(array2).max().orElse(Integer.MIN_VALUE);
    }

    private Task3() {
    }
}
