package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class GameDictionary implements Dictionary {
    private static final String[] WORDS = {"welcome", "game", "friend", "programming", "night"};
    private static final Random RANDOM = new Random();

    @Override
    public @NotNull String randomWord() {
        int index = RANDOM.nextInt(WORDS.length);
        return WORDS[index];
    }
}
