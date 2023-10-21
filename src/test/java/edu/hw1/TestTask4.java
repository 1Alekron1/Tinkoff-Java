package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask4 {

    @Test
    @DisplayName("Тест: Исправление строки с четным количеством символов (ожидается 'This is a mixed up string.')")
    void testFixStringEvenNumberOfCharThatReturnedExpectedValue() {
        final String EXPECTED_VALUE = "This is a mixed up string.";
        String result = Task4.fixString("hTsii  s aimex dpus rtni.g");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Исправление строки с нечетным количеством символов (ожидается 'abcde')")
    void testFixStringOddNumberOfCharThatReturnedExpectedValue() {
        final String EXPECTED_VALUE = "abcde";
        String result = Task4.fixString("badce");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }
}

