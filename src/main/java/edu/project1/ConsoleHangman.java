package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {

    public static final int MAX_ATTEMPTS = 5;
    private final static Logger LOGGER = LogManager.getLogger();

    private final Dictionary dictionary;

    public ConsoleHangman(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            Session session = new Session(dictionary.randomWord(), MAX_ATTEMPTS);

            while (!session.isOver()) {
                printState(session);
                LOGGER.info("Guess a letter: ");
                String guess = scanner.next();
                GuessResult result = session.guess(guess);
                LOGGER.info(result.message());
            }

            LOGGER.info("Play again? (yes/no)");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("yes");
        }

        LOGGER.info("Thanks for playing!");
    }

    private void printState(Session session) {
        char[] userAnswer = session.getUserAnswer();
        StringBuilder displayWord = new StringBuilder();

        for (char c : userAnswer) {
            if (c == '\u0000') {
                displayWord.append('*');
            } else {
                displayWord.append(c);
            }
        }

        LOGGER.info("The word: " + displayWord);
    }
}

