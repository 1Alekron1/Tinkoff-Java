package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask7 {
    @Test
    @DisplayName("Тест с null ключем")
    public void testTreeMapWithNullKey() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7.NullComparator());
        tree.put(null, "test");

        assertTrue(tree.containsKey(null));
    }
}
