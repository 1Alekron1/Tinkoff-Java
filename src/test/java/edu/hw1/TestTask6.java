package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask6 {

    @Test
    @DisplayName("Тест: Подсчет цифры K в обычном числе (ожидается 5)")
    void testCountKThatReturnedExpectedValueForRegularNumber() {
        final int EXPECTED_VALUE = 5;
        int result = Task6.countK(6621);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Подсчет цифры K в числе с одинаковыми цифрами (ожидается 0)")
    void testCountKThatReturnedExpectedValueForSameDigitsNumber() {
        final int EXPECTED_VALUE = 0;
        int result = Task6.countK(7777);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Подсчет цифры K в числе меньше 1000 (ожидается 0)")
    void testCountKThatReturnedExpectedValueForSmallNumber() {
        final int EXPECTED_VALUE = 0;
        int result = Task6.countK(997);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Подсчет цифры K в числе больше 10000 (ожидается 0)")
    void testCountKThatReturnedExpectedValueForBigNumber() {
        final int EXPECTED_VALUE = 0;
        int result = Task6.countK(12000);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }
}

