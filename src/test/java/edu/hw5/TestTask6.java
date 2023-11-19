package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Тесты для определения подпоследовательности")
public class TestTask6 {

    @Test
    @DisplayName("Тест: abc является подпоследовательностью achfdbaabgabcaabg")
    public void testIsSubsequence1() {
        assertTrue(Task6.isSubsequence("abc", "achfdbaabgabcaabg"));
    }

    @Test
    @DisplayName("Тест: abd не является подпоследовательностью achfdbaabgabcaabg")
    public void testIsSubsequence2() {
        assertFalse(Task6.isSubsequence("abd", "achfdbaabgabcaabg"));
    }

    @Test
    @DisplayName("Тест: пустая строка является подпоследовательностью любой строки")
    public void testIsSubsequence3() {
        assertTrue(Task6.isSubsequence("", "achfdbaabgabcaabg"));
    }

    @Test
    @DisplayName("Тест: xyz является подпоследовательностью xyz")
    public void testIsSubsequence4() {
        assertTrue(Task6.isSubsequence("xyz", "xyz"));
    }

    @Test
    @DisplayName("Тест: xy не является подпоследовательностью xzy")
    public void testIsSubsequence5() {
        assertFalse(Task6.isSubsequence("xy", "xzy"));
    }
}
