package edu.hw1;

public class Task4 {
    public static String fixString(String initialString) {
        var temporaryArray = new char[initialString.length()];
        var length = initialString.length();
        for (var i = 0; i < length - 1; i += 2) {
            temporaryArray[i] = initialString.charAt(i + 1);
            temporaryArray[i + 1] = initialString.charAt(i);
        }
        if (length % 2 == 1) {
            temporaryArray[length - 1] = initialString.charAt(length - 1);
        }
        return new String(temporaryArray);
    }

    private Task4() {
    }
}
