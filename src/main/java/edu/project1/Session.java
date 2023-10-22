package edu.project1;

import java.util.HashSet;
import java.util.Set;

public class Session {
    private final String answer;
    private char[] userAnswer;
    private final int maxAttempts;
    private int attempts;
    private final Set<Character> usedLetters;

    public Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.maxAttempts = maxAttempts;
        this.userAnswer = new char[answer.length()];
        this.usedLetters = new HashSet<>();
        this.attempts = 0;
    }

    public GuessResult guess(String guess) {
        boolean found = false;
        var lenght = guess.length();
        if (lenght == 1) {
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == guess.charAt(0)) {
                    userAnswer[i] = guess.charAt(0);
                    found = true;
                }
            }
        }
        String failMessage = null;

        if (!found && lenght == 1) {
            attempts++;
            if (attempts >= maxAttempts) {
                return new GuessResult.Defeat(
                    "You have lost" + ". The word was: " + answer);
            }
            failMessage = "Missed, mistake " + attempts + " out of " + maxAttempts + ".";
        }

        if (new String(userAnswer).equals(answer) || guess.equals(answer)) {
            userAnswer = answer.toCharArray();
            return new GuessResult.Win("The word: " + answer + "\nYou won!");
        }
        if (guess.length() > 1) {
            failMessage = "Typo, try again.";
        }
        if (usedLetters.contains(guess.charAt(0)) && lenght == 1) {
            failMessage = "You've already guessed this letter. Try another one.";
        }
        if (lenght == 1) {
            usedLetters.add(guess.charAt(0));
        }
        if (failMessage != null) {
            return new GuessResult.FailedGuess(failMessage);
        }

        return new GuessResult.SuccessfulGuess("Hit!");
    }

    public boolean isOver() {
        return attempts >= maxAttempts || new String(userAnswer).equals(answer);
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }
}
