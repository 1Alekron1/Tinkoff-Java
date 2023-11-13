package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты для Task3")
public class TestTask3 {

    @Test
    @DisplayName("Тест: валидный формат 'yyyy-MM-dd'")
    public void testValidFormat1() {
        Optional<LocalDate> result = Task3.parseDate("2023-10-29");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2023, 10, 29), result.get());
    }

    @Test
    @DisplayName("Тест: валидный формат 'yyyy-M-d'")
    public void testValidFormat2() {
        Optional<LocalDate> result = Task3.parseDate("2023-5-9");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2023, 5, 9), result.get());
    }

    @Test
    @DisplayName("Тест: валидный формат 'yyyy/MM/dd'")
    public void testValidFormat3() {
        Optional<LocalDate> result = Task3.parseDate("2023/10/29");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2023, 10, 29), result.get());
    }

    @Test
    @DisplayName("Тест: валидный формат 'yyyy/M/d'")
    public void testValidFormat4() {
        Optional<LocalDate> result = Task3.parseDate("2023/5/9");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2023, 5, 9), result.get());
    }

    @Test
    @DisplayName("Тест: валидный формат 'M/d/yyyy'")
    public void testValidFormat5() {
        Optional<LocalDate> result = Task3.parseDate("5/9/2023");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2023, 5, 9), result.get());
    }

    @Test
    @DisplayName("Тест: валидный формат 'M/d/yy'")
    public void testValidFormat6() {
        Optional<LocalDate> result = Task3.parseDate("5/9/23");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2023, 5, 9), result.get());
    }

    @Test
    @DisplayName("Тест: валидный формат 'MM/dd/yyyy'")
    public void testValidFormat7() {
        Optional<LocalDate> result = Task3.parseDate("05/09/2023");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2023, 5, 9), result.get());
    }

    @Test
    @DisplayName("Тест: валидный формат 'MM/dd/yy'")
    public void testValidFormat8() {
        Optional<LocalDate> result = Task3.parseDate("05/09/23");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2023, 5, 9), result.get());
    }

    @Test
    @DisplayName("Тест: валидный формат '1/3/1976'")
    public void testValidFormat9() {
        Optional<LocalDate> result = Task3.parseDate("1/3/1976");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(1976, 1, 3), result.get());
    }

    @Test
    @DisplayName("Тест: валидный формат '1/3/20'")
    public void testValidFormat10() {
        Optional<LocalDate> result = Task3.parseDate("1/3/20");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.of(2020, 1, 3), result.get());
    }

    @Test
    @DisplayName("Тест: относительная дата 'today'")
    public void testRelativeDateToday() {
        Optional<LocalDate> result = Task3.parseDate("today");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now(), result.get());
    }

    @Test
    @DisplayName("Тест: относительная дата 'tomorrow'")
    public void testRelativeDateTomorrow() {
        Optional<LocalDate> result = Task3.parseDate("tomorrow");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().plusDays(1), result.get());
    }

    @Test
    @DisplayName("Тест: относительная дата 'yesterday'")
    public void testRelativeDateYesterday() {
        Optional<LocalDate> result = Task3.parseDate("yesterday");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().minusDays(1), result.get());
    }

    @Test
    @DisplayName("Тест: относительная дата '2 days ago'")
    public void testRelativeDateDaysAgo() {
        Optional<LocalDate> result = Task3.parseDate("2 days ago");
        assertTrue(result.isPresent());
        assertEquals(LocalDate.now().minusDays(2), result.get());
    }

    @Test
    @DisplayName("Тест: невалидный формат")
    public void testInvalidFormat() {
        Optional<LocalDate> result = Task3.parseDate("InvalidFormat");
        assertFalse(result.isPresent());
    }
}
