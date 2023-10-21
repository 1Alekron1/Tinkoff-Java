package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask5 {

    @Test
    @DisplayName("Тест: Проверка числа со множеством потомков (ожидается true)")
    void testIsPalindromeDescendantThatReturnedTrueForManyDescendants() {
        final boolean EXPECTED_VALUE = true;
        boolean result = Task5.isPalindromeDescendant(11211230);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Проверка числа с одним потомком (ожидается true)")
    void testIsPalindromeDescendantThatReturnedTrueForOneDescendant() {
        final boolean EXPECTED_VALUE = true;
        boolean result = Task5.isPalindromeDescendant(123312);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Проверка числа меньше 10 (ожидается false)")
    void testIsPalindromeDescendantThatReturnedFalseForSmallNumber() {
        final boolean EXPECTED_VALUE = false;
        boolean result = Task5.isPalindromeDescendant(7);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Проверка нечетного числа (ожидается false)")
    void testIsPalindromeDescendantThatReturnedFalseForOddNumber() {
        final boolean EXPECTED_VALUE = false;
        boolean result = Task5.isPalindromeDescendant(345);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Проверка палиндрома (ожидается true)")
    void testIsPalindromeDescendantThatReturnedTrueForPalindromeNumber() {
        final boolean EXPECTED_VALUE = true;
        boolean result = Task5.isPalindromeDescendant(1221);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }
}

