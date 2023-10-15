package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SeventhTaskTest {
    @Test
    @DisplayName("Поворот по часовой стрелке без крайних случаев")
    void regularRight() {
        assertThat(4).isEqualTo(Task7.rotateRight(8, 1));
    }

    @Test
    @DisplayName("Поворот против часовой стрелке без крайних случаев")
    void regularLeft() {
        assertThat(6).isEqualTo(Task7.rotateLeft(17, 2));
    }

    @Test
    @DisplayName("Поворот по часовой стрелке, когда сдвиг больше длины числа в двоичной системе")
    void outsandingRight() {
        assertThat(4).isEqualTo(Task7.rotateRight(8, 5));
    }

    @Test
    @DisplayName("Поворот против часовой стрелке, когда сдвиг больше длины числа в двоичной системе")
    void outsandingLeft() {
        assertThat(16).isEqualTo(Task7.rotateLeft(16, 5));
    }
}
