package edu.hw3;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask4 {
    @Test
    @DisplayName("Тест: Преобразование арабского числа 2 в римское")
    void testConvertToRomanFor2() {
        String result = Task4.convertToRoman(2);
        assertEquals("II", result);
    }

    @Test
    @DisplayName("Тест: Преобразование арабского числа 12 в римское")
    void testConvertToRomanFor12() {
        String result = Task4.convertToRoman(12);
        assertEquals("XII", result);
    }

    @Test
    @DisplayName("Тест: Преобразование арабского числа 16 в римское")
    void testConvertToRomanFor16() {
        String result = Task4.convertToRoman(16);
        assertEquals("XVI", result);
    }

    @Test
    @DisplayName("Тест: Преобразование арабского числа 3999 в римское")
    void testConvertToRomanFor3999() {
        String result = Task4.convertToRoman(3999);
        assertEquals("MMMCMXCIX", result);
    }

    @Test
    @DisplayName("Тест: Преобразование арабского числа 0 (недопустимое значение)")
    void testConvertToRomanForZero() {
        String result = Task4.convertToRoman(0);
        assertEquals("ivalid value", result);
    }

    @Test
    @DisplayName("Тест: Преобразование арабского числа -5 (недопустимое значение)")
    void testConvertToRomanForNegativeNumber() {
        String result = Task4.convertToRoman(-5);
        assertEquals("ivalid value", result);
    }

    @Test
    @DisplayName("Тест: Преобразование арабского числа 4000 (недопустимое значение)")
    void testConvertToRomanForOutOfRangeNumber() {
        String result = Task4.convertToRoman(4000);
        assertEquals("ivalid value", result);
    }
}
