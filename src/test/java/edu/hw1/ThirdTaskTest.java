package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ThirdTaskTest {
    @Test
    @DisplayName("Тест на несовпадающих минимумах и максимумах в разных массивах")
    void differentMinMaxValues() {
        assertThat(true).isEqualTo(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6}));
    }

    @Test
    @DisplayName("Тест на совпадающих минимумах и максимумах в разных массивах")
    void equalMinMaxValues() {
        assertThat(false).isEqualTo(Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9}));
    }
}
