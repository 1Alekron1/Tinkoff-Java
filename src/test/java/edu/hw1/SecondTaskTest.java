package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SecondTaskTest {
    @Test
    @DisplayName("Тест на большом числе")
    void bigInteger() {
        assertThat(7).isEqualTo(Task2.countDigits(4666777));
    }

    @Test
    @DisplayName("Тест на нуле")
    void zeroTest() {
        assertThat(1).isEqualTo(Task2.countDigits(0));
    }
}
