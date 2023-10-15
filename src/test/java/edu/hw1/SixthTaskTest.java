package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SixthTaskTest {
    @Test
    @DisplayName("Тест на обычном числе")
    void regularNumber() {
        assertThat(5).isEqualTo(Task6.countK(6621));
    }

    @Test
    @DisplayName("Тест на числе с одинаковыми цифрами")
    void sameDigitsNumber() {
        assertThat(0).isEqualTo(Task6.countK(7777));
    }

    @Test
    @DisplayName("Тест на числе меньше 1000")
    void smallNumber() {
        assertThat(0).isEqualTo(Task6.countK(997));
    }

    @Test
    @DisplayName("Тест на числе больше 10000")
    void bigNumber() {
        assertThat(0).isEqualTo(Task6.countK(12000));
    }
}
