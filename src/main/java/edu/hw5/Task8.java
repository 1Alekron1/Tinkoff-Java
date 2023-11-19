package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {
    private Task8() {
    }

    // Нечетной длины
    public static boolean isOddLength(String input) {
        String regex = "^(.{2})*.$";
        return Pattern.matches(regex, input);
    }

    // Начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public static boolean hasSpecificLengthAndStart(String input) {
        String regex = "^(0.{1,2})*$|^1(.{2})*$";
        return Pattern.matches(regex, input);
    }

    // Любая строка, кроме 11 или 111
    public static boolean notEqualToSpecificStrings(String input) {
        String regex = "^(?!11$|111$).*$";
        return Pattern.matches(regex, input);
    }

    // Каждый нечетный символ равен 1
    public static boolean oddDigitsAreOnes(String input) {
        String regex = "^0*1(01)*$";
        return Pattern.matches(regex, input);
    }

    // Нет последовательных 1
    public static boolean noConsecutiveOnes(String input) {
        String regex = "^(?!.*11).*$";
        return Pattern.matches(regex, input);
    }
}
