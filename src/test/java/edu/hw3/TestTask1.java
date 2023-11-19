package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask1 {

    @Test
    @DisplayName("Тест: Преобразование строки с латинскими символами")
    void testLatinStringThatReturnedExpectedValue() {
        final String EXPECTED_VALUE = "Svool dliow!";
        String result = Task1.atbash("Hello world!");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Преобразование строки с не латинскими символами")
    void testNonLatinStringThatReturnedExpectedValue() {
        final String EXPECTED_VALUE = "Привет123";
        String result = Task1.atbash("Привет123");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Преобразование пустой строки")
    void testEmptyStringThatReturnedExpectedValue() {
        final String EXPECTED_VALUE = "";
        String result = Task1.atbash("");
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }
}
