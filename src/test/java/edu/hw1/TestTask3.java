package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask3 {

    @Test
    @DisplayName("Тест: Проверка вложенности массивов с несовпадающими минимумами и максимумами (ожидается true)")
    void testIsNestableThatReturnedTrue() {
        final boolean EXPECTED_VALUE = true;
        boolean result = Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6});
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }

    @Test
    @DisplayName("Тест: Проверка вложенности массивов с совпадающими минимумами и максимумами (ожидается false)")
    void testIsNotNestableThatReturnedFalse() {
        final boolean EXPECTED_VALUE = false;
        boolean result = Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9});
        assertThat(result).isEqualTo(EXPECTED_VALUE);
    }
}

