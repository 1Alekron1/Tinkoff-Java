package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FifthTaskTest {
    @Test
    @DisplayName("Тест на числе со множеством потомков")
    void manyDescendants() {
        assertThat(true).isEqualTo(Task5.isPalindromeDescendant(11211230));
    }

    @Test
    @DisplayName("Тест на числе с одним потомком")
    void oneDescendants() {
        assertThat(true).isEqualTo(Task5.isPalindromeDescendant(123312));
    }

    @Test
    @DisplayName("Тест на числе меньше 10")
    void smallNumber() {
        assertThat(false).isEqualTo(Task5.isPalindromeDescendant(7));
    }

    @Test
    @DisplayName("Тест на нечетном числе")
    void oddNumber() {
        assertThat(false).isEqualTo(Task5.isPalindromeDescendant(345));
    }

    @Test
    @DisplayName("Тест на палиндроме")
    void palindromeNumber() {
        assertThat(true).isEqualTo(Task5.isPalindromeDescendant(1221));
    }
}
