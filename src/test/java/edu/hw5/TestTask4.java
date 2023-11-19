package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Тесты для проверки требований к паролю")
public class TestTask4 {

    @Test
    @DisplayName("Тест: пароль содержит символ '~'")
    public void testPasswordContainsTilde() {
        assertTrue(Task4.containsSpecialCharacter("~Secure123"));
    }

    @Test
    @DisplayName("Тест: пароль содержит символ '!'")
    public void testPasswordContainsExclamationMark() {
        assertTrue(Task4.containsSpecialCharacter("Pass!word"));
    }

    @Test
    @DisplayName("Тест: пароль содержит символ '@'")
    public void testPasswordContainsAtSymbol() {
        assertTrue(Task4.containsSpecialCharacter("User@123"));
    }

    @Test
    @DisplayName("Тест: пароль содержит символ '#'")
    public void testPasswordContainsHashSymbol() {
        assertTrue(Task4.containsSpecialCharacter("Secret#2022"));
    }

    @Test
    @DisplayName("Тест: пароль содержит символ '$'")
    public void testPasswordContainsDollarSign() {
        assertTrue(Task4.containsSpecialCharacter("Money$123"));
    }

    @Test
    @DisplayName("Тест: пароль содержит символ '%'")
    public void testPasswordContainsPercentSymbol() {
        assertTrue(Task4.containsSpecialCharacter("Percentage%"));
    }

    @Test
    @DisplayName("Тест: пароль содержит символ '^'")
    public void testPasswordContainsCaretSymbol() {
        assertTrue(Task4.containsSpecialCharacter("Up^Down"));
    }

    @Test
    @DisplayName("Тест: пароль содержит символ '&'")
    public void testPasswordContainsAmpersandSymbol() {
        assertTrue(Task4.containsSpecialCharacter("Smith&Jones"));
    }

    @Test
    @DisplayName("Тест: пароль содержит символ '*'")
    public void testPasswordContainsAsteriskSymbol() {
        assertTrue(Task4.containsSpecialCharacter("Stars*123"));
    }

    @Test
    @DisplayName("Тест: пароль содержит символ '|'")
    public void testPasswordContainsPipeSymbol() {
        assertTrue(Task4.containsSpecialCharacter("Pipe|line"));
    }

    @Test
    @DisplayName("Тест: пароль не содержит требуемых символов")
    public void testPasswordDoesNotContainRequiredSymbols() {
        assertFalse(Task4.containsSpecialCharacter("SecurePassword123"));
    }
}
