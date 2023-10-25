package edu.hw1;

public class Task5 {
    final static int MINIMUM_VALUE = 10;

    public static boolean isPalindromeDescendant(int number) {
        if (isPalindrome(number)) {
            return true;
        }
        if (String.valueOf(number).length() % 2 == 1 || number < MINIMUM_VALUE) {
            return false;
        }
        var numberString = String.valueOf(number);
        var descendant = new StringBuilder();
        for (int i = 0; i < numberString.length() - 1; i += 2) {
            int digit1 = Character.getNumericValue(numberString.charAt(i));
            int digit2 = Character.getNumericValue(numberString.charAt(i + 1));
            descendant.append(digit1 + digit2);
        }
        return isPalindromeDescendant(Integer.parseInt(descendant.toString()));
    }

    public static boolean isPalindrome(int number) {
        if (number < MINIMUM_VALUE) {
            return false;
        }
        var numberString = String.valueOf(number);
        return numberString.contentEquals(new StringBuilder(numberString).reverse());
    }

    private Task5() {
    }
}
