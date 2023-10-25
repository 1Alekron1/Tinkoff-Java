package edu.project1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestProject1 {

    @Test
    @DisplayName("Поражение после превышения максимального числа попыток")
    public void testMaxAttemptsThatReturnedTrue() {
        final String ANSWER = "виселица";
        final int MAX_ATTEMPTS = 1;
        final String GUESS = "б";

        Dictionary dictionary = new GameDictionary();
        Session session = new Session(ANSWER, MAX_ATTEMPTS);
        GuessResult result = session.guess(GUESS);
        assertTrue(result instanceof GuessResult.Defeat);
    }

    @Test
    @DisplayName("Корректное изменение состояния игры")
    public void testGameStateChangeThatReturnedTrue() {
        final String ANSWER = "программирование";
        final int MAX_ATTEMPTS = 5;
        final String GUESS_1 = "п";
        final String GUESS_2 = "б";

        Dictionary dictionary = new GameDictionary();
        Session session = new Session(ANSWER, MAX_ATTEMPTS);
        session.guess(GUESS_1);
        GuessResult result = session.guess(GUESS_2);
        assertTrue(result instanceof GuessResult.FailedGuess);
        assertEquals("Missed, mistake 1 out of 5.", result.message());
    }

    @Test
    @DisplayName("Некорректный ввод приводит к повторному вводу без изменения состояния")
    public void testIncorrectInputThatReturnedTrue() {
        final String ANSWER = "компьютер";
        final int MAX_ATTEMPTS = 5;
        final String GUESS = "аб";

        Dictionary dictionary = new GameDictionary();
        Session session = new Session(ANSWER, MAX_ATTEMPTS);
        GuessResult result = session.guess(GUESS);
        assertTrue(result instanceof GuessResult.FailedGuess);
        assertEquals("Typo, try again.", result.message());
        char[] userAnswer = session.getUserAnswer();
        assertArrayEquals(
            new char[] {'\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000'},
            userAnswer
        );
    }

    @Test
    @DisplayName("Проверка на повторный ввод при некорректной длине ввода без изменения состояния")
    public void testIncorrectInputLengthThatReturnedFalse() {
        final String ANSWER = "игра";
        final int MAX_ATTEMPTS = 5;
        final String GUESS_1 = "а";
        final String GUESS_2 = "аб";

        Dictionary dictionary = new GameDictionary();
        Session session = new Session(ANSWER, MAX_ATTEMPTS);
        GuessResult result = session.guess(GUESS_1);
        assertEquals("Hit!", result.message());
        result = session.guess(GUESS_2);
        char[] userAnswer = session.getUserAnswer();
        assertArrayEquals(new char[] {'\0', '\0', '\0', 'а'}, userAnswer);
        assertTrue(result instanceof GuessResult.FailedGuess);
        assertEquals("Typo, try again.", result.message());
    }
}
