package edu.hw3;

public class Task4 {

    public static final int[] ARABIC_VALUES = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public static final String[] ROMAN_SYMBOLS =
        new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static final int MAX_VALUE = 3999;

    public static String convertToRoman(int number) {
        int curNumber = number;
        if (curNumber <= 0 || curNumber > MAX_VALUE) {
            return "ivalid value";
        }

        StringBuilder romanNumeral = new StringBuilder();
        int index = 0;

        while (curNumber > 0) {
            if (curNumber >= ARABIC_VALUES[index]) {
                romanNumeral.append(ROMAN_SYMBOLS[index]);
                curNumber -= ARABIC_VALUES[index];
            } else {
                index++;
            }
        }

        return romanNumeral.toString();
    }

    private Task4() {

    }
}
