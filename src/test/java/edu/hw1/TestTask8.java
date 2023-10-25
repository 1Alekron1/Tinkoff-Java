package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask8 {

    @Test
    @DisplayName("Тест: Проверка доски без пересечений (ожидается true)")
    void testKnightBoardCaptureThatReturnedTrueForNormalBoard() {
        final boolean EXPECTED_VALUE = true;
        boolean result = Task8.knightBoardCapture(new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        });
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Проверка доски с пересечениями (ожидается false)")
    void testKnightBoardCaptureThatReturnedFalseForIntersectionBoard() {
        final boolean EXPECTED_VALUE = false;
        boolean result = Task8.knightBoardCapture(new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        });
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Проверка доски без коней (ожидается true)")
    void testKnightBoardCaptureThatReturnedTrueForNoKnightsBoard() {
        final boolean EXPECTED_VALUE = true;
        boolean result = Task8.knightBoardCapture(new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        });
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Проверка доски с одним конем (ожидается true)")
    void testKnightBoardCaptureThatReturnedTrueForOneKnightsBoard() {
        final boolean EXPECTED_VALUE = true;
        boolean result = Task8.knightBoardCapture(new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        });
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Проверка доски, где все клетки заняты конями (ожидается false)")
    void testKnightBoardCaptureThatReturnedFalseForAllKnightsBoard() {
        final boolean EXPECTED_VALUE = false;
        boolean result = Task8.knightBoardCapture(new int[][] {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        });
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }
}

