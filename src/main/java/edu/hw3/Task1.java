package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task1 {

    private static Map<Character, Character> atbashMap;

    public static String atbash(String str) {
        if (atbashMap == null) {
            generateMap();
        }

        var newString = new StringBuilder();
        for (var c : str.toCharArray()) {
            newString.append(atbashMap.getOrDefault(c, c));
        }

        return newString.toString();
    }

    private static void generateMap() {
        atbashMap = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            char newChar = (char) ('z' - (c - 'a'));
            atbashMap.put(c, newChar);
            atbashMap.put(Character.toUpperCase(c), Character.toUpperCase(newChar));
        }
    }

    private Task1() {

    }
}
