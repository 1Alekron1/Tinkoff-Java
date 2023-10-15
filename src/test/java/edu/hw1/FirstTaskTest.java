package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FirstTaskTest {
    @Test
    @DisplayName("Тест на правильной строке")
    void regularString() {
        assertThat(836).isEqualTo(Task1.minutesToSeconds("13:56"));
    }

    @Test
    @DisplayName("Секунд больше 60")
    void secondsMoreThanSixty() {
        assertThat(-1).isEqualTo(Task1.minutesToSeconds("10:65"));
    }

    @Test
    @DisplayName("Секунд меньше 0")
    void secondsLessThanZero() {
        assertThat(-1).isEqualTo(Task1.minutesToSeconds("10:-123"));
    }

    @Test
    @DisplayName("Минут меньше 0")
    void minutesLessThanZero() {
        assertThat(-1).isEqualTo(Task1.minutesToSeconds("-4:34"));
    }

    @Test
    @DisplayName("Минут больше 60")
    void minutesMoreThanSixty() {
        assertThat(59963).isEqualTo(Task1.minutesToSeconds("999:23"));
    }
}
