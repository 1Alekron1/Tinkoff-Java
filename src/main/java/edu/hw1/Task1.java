package edu.hw1;

public class Task1 {

    final static int MINUTES_IN_SECONDS = 60;

    public static int minutesToSeconds(String videoLength) {
        String[] splittedString = videoLength.split(":");
        int minutes = Integer.parseInt(splittedString[0]);
        int seconds = Integer.parseInt(splittedString[1]);
        if (seconds >= MINUTES_IN_SECONDS || seconds < 0 || minutes < 0) {
            return -1;
        }
        return minutes * MINUTES_IN_SECONDS + seconds;
    }

    private Task1() {

    }
}
