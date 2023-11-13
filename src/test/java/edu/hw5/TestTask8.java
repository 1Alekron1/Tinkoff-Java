package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Тесты для проверки регулярных выражений")
public class TestTask8 {

    @Test
    @DisplayName("Тест: isOddLength с валидной строкой")
    public void testIsOddLengthValid() {
        assertTrue(Task8.isOddLength("101"));
    }

    @Test
    @DisplayName("Тест: isOddLength с невалидной строкой")
    public void testIsOddLengthInvalid() {
        assertFalse(Task8.isOddLength("11"));
    }

    @Test
    @DisplayName("Тест: hasSpecificLengthAndStart с валидной строкой")
    public void testHasSpecificLengthAndStartValid() {
        assertTrue(Task8.hasSpecificLengthAndStart("010"));
    }

    @Test
    @DisplayName("Тест: hasSpecificLengthAndStart с невалидной строкой")
    public void testHasSpecificLengthAndStartInvalid() {
        assertFalse(Task8.hasSpecificLengthAndStart("1101"));
    }

    @Test
    @DisplayName("Тест: notEqualToSpecificStrings с валидной строкой")
    public void testNotEqualToSpecificStringsValid() {
        assertTrue(Task8.notEqualToSpecificStrings("101"));
    }

    @Test
    @DisplayName("Тест: notEqualToSpecificStrings с невалидной строкой")
    public void testNotEqualToSpecificStringsInvalid() {
        assertFalse(Task8.notEqualToSpecificStrings("111"));
    }

    @Test
    @DisplayName("Тест: oddDigitsAreOnes с валидной строкой")
    public void testOddDigitsAreOnesValid() {
        assertTrue(Task8.oddDigitsAreOnes("101"));
    }

    @Test
    @DisplayName("Тест: oddDigitsAreOnes с невалидной строкой")
    public void testOddDigitsAreOnesInvalid() {
        assertFalse(Task8.oddDigitsAreOnes("100"));
    }

    @Test
    @DisplayName("Тест: noConsecutiveOnes с валидной строкой")
    public void testNoConsecutiveOnesValid() {
        assertTrue(Task8.noConsecutiveOnes("1010"));
    }

    @Test
    @DisplayName("Тест: noConsecutiveOnes с невалидной строкой")
    public void testNoConsecutiveOnesInvalid() {
        assertFalse(Task8.noConsecutiveOnes("110"));
    }
}
