package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FourthTaskTest {
    @Test
    @DisplayName("Тест на строке с четным количеством символов")
    void evenNumberOfChar() {
        assertThat("This is a mixed up string.").isEqualTo(Task4.fixString("hTsii  s aimex dpus rtni.g"));
    }

    @Test
    @DisplayName("Тест на строке с нечетным количеством символов")
    void oddNumberOfChar() {
        assertThat("abcde").isEqualTo(Task4.fixString("badce"));
    }
}
