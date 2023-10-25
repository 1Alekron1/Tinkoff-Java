package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask1 {

    @Test
    @DisplayName("Тест: Преобразование правильной строки в секунды (ожидается 836)")
    void testRegularStringThatReturnedExpectedValue() {
        final int EXPECTED_VALUE = 836;
        int result = Task1.minutesToSeconds("13:56");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Секунды больше 60 (ожидается -1)")
    void testSecondsMoreThanSixtyThatReturnedExpectedValue() {
        final int EXPECTED_VALUE = -1;
        int result = Task1.minutesToSeconds("10:65");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Секунды меньше 0 (ожидается -1)")
    void testSecondsLessThanZeroThatReturnedExpectedValue() {
        final int EXPECTED_VALUE = -1;
        int result = Task1.minutesToSeconds("10:-123");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Минуты меньше 0 (ожидается -1)")
    void testMinutesLessThanZeroThatReturnedExpectedValue() {
        final int EXPECTED_VALUE = -1;
        int result = Task1.minutesToSeconds("-4:34");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Минуты больше 60 (ожидается 59963)")
    void testMinutesMoreThanSixtyThatReturnedExpectedValue() {
        final int EXPECTED_VALUE = 59963;
        int result = Task1.minutesToSeconds("999:23");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }
}
