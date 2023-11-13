package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Тесты для проверки регулярных выражений")
public class TestTask7 {

    @Test
    @DisplayName("Тест: checkThirdSymbol с валидной строкой")
    public void testCheckThirdSymbolValid() {
        assertTrue(Task7.checkThirdSymbol("010"));
    }

    @Test
    @DisplayName("Тест: checkThirdSymbol с невалидной строкой")
    public void testCheckThirdSymbolInvalid() {
        assertFalse(Task7.checkThirdSymbol("011"));
    }

    @Test
    @DisplayName("Тест: checkStartAndEnd с валидной строкой")
    public void testCheckStartAndEndValid() {
        assertTrue(Task7.checkStartAndEnd("010"));
    }

    @Test
    @DisplayName("Тест: checkStartAndEnd с невалидной строкой")
    public void testCheckStartAndEndInvalid() {
        assertFalse(Task7.checkStartAndEnd("011"));
    }

    @Test
    @DisplayName("Тест: checkLength с валидной строкой")
    public void testCheckLengthValid() {
        assertTrue(Task7.checkLength("010"));
    }

    @Test
    @DisplayName("Тест: checkLength с невалидной строкой")
    public void testCheckLengthInvalid() {
        assertFalse(Task7.checkLength("0111"));
    }
}
