package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static final int MINUTES_IN_SECONDS = 60;

    private Task1() {
    }

    public static String calculateAnalytics(List<String> sessions) {
        List<Duration> durations = calculateDurations(sessions);

        if (durations.isEmpty()) {
            return 0 + "ч " + 0 + "м";
        }
        long totalHours = 0;
        long totalMinutes = 0;

        for (Duration duration : durations) {
            totalMinutes += duration.toMinutes();
        }

        totalMinutes /= durations.size();
        totalHours += totalMinutes / MINUTES_IN_SECONDS;
        totalMinutes %= MINUTES_IN_SECONDS;

        return (totalHours + "ч " + totalMinutes + "м");
    }

    private static List<Duration> calculateDurations(List<String> sessions) {
        List<Duration> durations = new ArrayList<>();
        for (String session : sessions) {
            String[] sessionParts = session.split(" - ");
            LocalDateTime startDateTime = parseDateTime(sessionParts[0]);
            LocalDateTime endDateTime = parseDateTime(sessionParts[1]);

            durations.add(Duration.between(startDateTime, endDateTime));
        }

        return durations;
    }

    private static LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}
