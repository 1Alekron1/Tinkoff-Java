package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    public static <T> Map<T, Integer> freqDict(List<T> elements) {
        Map<T, Integer> freqMap = new HashMap<>();

        for (T item : elements) {
            freqMap.put(item, freqMap.getOrDefault(item, 0) + 1);
        }

        return freqMap;
    }

    private Task3() {

    }
}

