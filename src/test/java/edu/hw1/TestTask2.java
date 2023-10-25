package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {

    @Test
    @DisplayName("Тест: Подсчет количества цифр в большом числе (ожидается 7)")
    void testCountDigitsInBigIntegerThatReturnedExpectedValue() {
        final int EXPECTED_VALUE = 7;
        int result = Task2.countDigits(4666777);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Подсчет количества цифр в нуле (ожидается 1)")
    void testCountDigitsInZeroThatReturnedExpectedValue() {
        final int EXPECTED_VALUE = 1;
        int result = Task2.countDigits(0);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }
}
