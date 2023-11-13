package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Task3 {

    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String input) {
        try {
            Optional<LocalDate> answer = Optional.empty();
            LocalDate date = parseWithFormat(input, "yyyy-MM-dd");
            if (date != null) {
                answer = Optional.of(date);
            }

            date = parseWithFormat(input, "yyyy-M-d");
            if (date != null) {
                answer = Optional.of(date);
            }

            date = parseWithFormat(input, "yyyy/MM/dd");
            if (date != null) {
                answer = Optional.of(date);
            }

            date = parseWithFormat(input, "yyyy/M/d");
            if (date != null) {
                answer = Optional.of(date);
            }

            date = parseWithFormat(input, "M/d/yyyy");
            if (date != null) {
                answer = Optional.of(date);
            }

            date = parseWithFormat(input, "M/d/yy");
            if (date != null) {
                answer = Optional.of(date);
            }

            date = parseWithFormat(input, "MM/dd/yyyy");
            if (date != null) {
                answer = Optional.of(date);
            }

            date = parseWithFormat(input, "MM/dd/yy");
            if (date != null) {
                answer = Optional.of(date);
            }

            date = parseWithFormat(input, "1/3/1976");
            if (date != null) {
                answer = Optional.of(date);
            }

            date = parseWithFormat(input, "1/3/20");
            if (date != null) {
                answer = Optional.of(date);
            }

            // Обработка относительных дат
            if (input.equalsIgnoreCase("today")) {
                answer = Optional.of(LocalDate.now());
            } else if (input.equalsIgnoreCase("tomorrow")) {
                answer = Optional.of(LocalDate.now().plusDays(1));
            } else if (input.equalsIgnoreCase("yesterday")) {
                answer = Optional.of(LocalDate.now().minusDays(1));
            } else if (input.matches("\\d+\\s+day[s]?\\s+ago")) {
                int daysAgo = Integer.parseInt(input.split("\\s+")[0]);
                answer = Optional.of(LocalDate.now().minusDays(daysAgo));
            }
            return answer;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static LocalDate parseWithFormat(String input, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            return LocalDate.parse(input, formatter);
        } catch (Exception e) {
            return null;
        }
    }
}
