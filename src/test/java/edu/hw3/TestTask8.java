package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask8 {
    @Test
    @DisplayName("Тест на обход коллекции назад")
    public void testBackwardIteration() {
        List<Integer> list = List.of(1, 2, 3);
        Task8.BackwardIterator<Integer> iterator = new Task8.BackwardIterator<>(list);

        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Тест на пустую коллекцию")
    public void testEmptyCollection() {
        List<Integer> list = List.of();
        Task8.BackwardIterator<Integer> iterator = new Task8.BackwardIterator<>(list);

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("Тест на один элемент")
    public void testSingleElement() {
        List<Integer> list = List.of(42);
        Task8.BackwardIterator<Integer> iterator = new Task8.BackwardIterator<>(list);

        assertTrue(iterator.hasNext());
        assertEquals(42, iterator.next());

        assertFalse(iterator.hasNext());
    }
}
