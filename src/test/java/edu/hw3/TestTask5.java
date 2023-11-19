package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestTask5 {
    @Test
    @DisplayName("Тест сортировки по фамилии по возрастанию")
    public void testParseContactsAscending() {
        String[] names = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String order = "ASC";
        String[] expected = {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"};
        assertArrayEquals(expected, Task5.parseContacts(names, order));
    }

    @Test
    @DisplayName("Тест сортировки по фамилии по убыванию")
    public void testParseContactsDescending() {
        String[] names = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String order = "DESC";
        String[] expected = {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};
        assertArrayEquals(expected, Task5.parseContacts(names, order));
    }

    @Test
    @DisplayName("Тест сортировки по фамилии (имени) по возрастанию")
    public void testParseContactsWithoutSurnameAscending() {
        String[] names = {"Aoul", "Leonhard Euler", "Carl Gauss"};
        String order = "ASC";
        String[] expected = {"Aoul", "Leonhard Euler", "Carl Gauss"};
        assertArrayEquals(expected, Task5.parseContacts(names, order));
    }

    @Test
    @DisplayName("Тест сортировки пустого массива")
    public void testParseContactsEmptyArray() {
        String[] names = {};
        String order = "DESC";
        String[] expected = {};
        assertArrayEquals(expected, Task5.parseContacts(names, order));
    }

    @Test
    @DisplayName("Тест сортировки массива с null")
    public void testParseContactsNullArray() {
        String[] names = null;
        String order = "DESC";
        String[] expected = {};
        assertArrayEquals(expected, Task5.parseContacts(names, order));
    }
}
