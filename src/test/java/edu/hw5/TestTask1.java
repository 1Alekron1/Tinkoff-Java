package edu.hw5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для расчета среднего времени в клубе")
public class TestTask1 {

    @Test
    @DisplayName("Тест на корректный расчет среднего времени")
    public void testAverageTimeCalculation() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );

        assertEquals("3ч 40м", Task1.calculateAnalytics(sessions));
    }

    @Test
    @DisplayName("Тест на пустой список сеансов")
    public void testEmptySessionsList() {
        List<String> sessions = List.of();

        assertEquals("0ч 0м", Task1.calculateAnalytics(sessions));
    }

    @Test
    @DisplayName("Тест на один сеанс")
    public void testSingleSession() {
        List<String> sessions = List.of("2022-03-12, 20:20 - 2022-03-12, 23:50");

        assertEquals("3ч 30м", Task1.calculateAnalytics(sessions));
    }
}
