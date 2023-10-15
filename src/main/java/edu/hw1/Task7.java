package edu.hw1;

import java.util.Arrays;

public class Task7 {
    public static int rotateRight(int n, int shift) {
        int[] binaryArray = convertToBinaryArray(n);
        var tempShift = shift % binaryArray.length;
        var shiftedPart = Arrays.copyOfRange(binaryArray, binaryArray.length - tempShift, binaryArray.length);
        var rightPart = Arrays.copyOfRange(binaryArray, 0, binaryArray.length - tempShift);
        var combinedArray = new int[shiftedPart.length + rightPart.length];

        System.arraycopy(shiftedPart, 0, combinedArray, 0, shiftedPart.length);
        System.arraycopy(rightPart, 0, combinedArray, shiftedPart.length, rightPart.length);
        return Integer.parseInt(concatenateDigits(combinedArray), 2);
    }

    public static int rotateLeft(int n, int shift) {
        int[] binaryArray = convertToBinaryArray(n);
        var tempShift = shift % binaryArray.length;
        var shiftedPart = Arrays.copyOfRange(binaryArray, 0, tempShift);
        var leftPart = Arrays.copyOfRange(binaryArray, tempShift, binaryArray.length);
        var combinedArray = new int[shiftedPart.length + leftPart.length];

        System.arraycopy(leftPart, 0, combinedArray, 0, leftPart.length);
        System.arraycopy(shiftedPart, 0, combinedArray, leftPart.length, shiftedPart.length);
        return Integer.parseInt(concatenateDigits(combinedArray), 2);
    }

    public static String concatenateDigits(int[] digits) {
        StringBuilder sb = new StringBuilder();

        for (int digit : digits) {
            sb.append(digit);
        }

        return sb.toString();
    }

    public static int[] convertToBinaryArray(int number) {
        var binaryString = Integer.toBinaryString(number);
        int[] binaryArray = new int[binaryString.length()];

        for (int i = 0; i < binaryString.length(); i++) {
            binaryArray[i] = Character.getNumericValue(binaryString.charAt(i));
        }

        return binaryArray;
    }

    private Task7() {
    }
}
