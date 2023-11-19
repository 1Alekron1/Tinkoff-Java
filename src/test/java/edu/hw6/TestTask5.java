package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTask5 {

    @Test
    @DisplayName("Test hackerNewsTopStories")
    void testHackerNewsTopStories() {
        long[] topStories = HackerNews.hackerNewsTopStories();
        assertNotNull(topStories);
        assertTrue(topStories.length > 0);
    }

    @Test
    @DisplayName("Test news")
    void testNews() {
        long newsId = 37570037;
        String newsTitle = HackerNews.news(newsId);
        assertNotNull(newsTitle);
        assertFalse(newsTitle.isEmpty());
    }
}
