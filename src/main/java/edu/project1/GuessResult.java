package edu.project1;

import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {

    @NotNull String message();

    record Defeat(String message) implements GuessResult {
    }

    record Win(String message) implements GuessResult {
    }

    record SuccessfulGuess(String message) implements GuessResult {
    }

    record FailedGuess(String message) implements GuessResult {
    }
}
