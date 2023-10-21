package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask7 {

    @Test
    @DisplayName("Тест: Поворот по часовой стрелке без крайних случаев (ожидается 4)")
    void testRotateRightThatReturnedExpectedValueForRegularRight() {
        final int EXPECTED_VALUE = 4;
        int result = Task7.rotateRight(8, 1);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Поворот против часовой стрелке без крайних случаев (ожидается 6)")
    void testRotateLeftThatReturnedExpectedValueForRegularLeft() {
        final int EXPECTED_VALUE = 6;
        int result = Task7.rotateLeft(17, 2);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Поворот по часовой стрелке, когда сдвиг больше длины числа в двоичной системе (ожидается 4)")
    void testRotateRightThatReturnedExpectedValueForOutstandingRight() {
        final int EXPECTED_VALUE = 4;
        int result = Task7.rotateRight(8, 5);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName(
        "Тест: Поворот против часовой стрелке, когда сдвиг больше длины числа в двоичной системе (ожидается 16)")
    void testRotateLeftThatReturnedExpectedValueForOutstandingLeft() {
        final int EXPECTED_VALUE = 16;
        int result = Task7.rotateLeft(16, 5);
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }
}

