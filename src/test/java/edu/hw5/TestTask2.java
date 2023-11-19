package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для поиска пятниц 13-го числа")
public class TestTask2 {

    @Test
    @DisplayName("Тест: корректный поиск пятниц 13-го числа для 1925 года")
    public void testFindFridays13thIn1925() {
        List<LocalDate> fridays13th = Task2.findFridayThe13ths(1925);
        List<LocalDate> expected = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );
        assertEquals(expected, fridays13th);
    }

    @Test
    @DisplayName("Тест: корректный поиск пятниц 13-го числа для 2024 года")
    public void testFindFridays13thIn2024() {
        List<LocalDate> fridays13th = Task2.findFridayThe13ths(2024);
        List<LocalDate> expected = List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)
        );
        assertEquals(expected, fridays13th);
    }

    @Test
    @DisplayName("Тест: корректный поиск следующей ближайшей пятницы 13-го числа от 2023-11-10")
    public void testFindNextFriday13thFrom2023_11_10() {
        LocalDate nextFriday13th = Task2.findNextFridayThe13th(LocalDate.of(2023, 11, 10));
        assertEquals(LocalDate.of(2024, 9, 13), nextFriday13th);
    }

    @Test
    @DisplayName("Тест: корректный поиск следующей ближайшей пятницы 13-го числа от 2025-05-20")
    public void testFindNextFriday13thFrom2025_05_20() {
        LocalDate nextFriday13th = Task2.findNextFridayThe13th(LocalDate.of(2025, 5, 20));
        assertEquals(LocalDate.of(2025, 6, 13), nextFriday13th);
    }
}
