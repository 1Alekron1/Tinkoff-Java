package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask3 {

    @Test
    @DisplayName("Тест: Частотный словарь для строк (различный порядок элементов)")
    void testFrequencyDictionaryForStringsDifferentOrder() {
        List<String> stringList = List.of("bb", "a", "bb", "a");
        Map<String, Integer> result = Task3.freqDict(stringList);
        Map<String, Integer> expected = Map.of("a", 2, "bb", 2);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Тест: Частотный словарь для целых чисел")
    void testFrequencyDictionaryForIntegers() {
        List<Integer> integerList = List.of(1, 1, 2, 2);
        Map<Integer, Integer> result = Task3.freqDict(integerList);
        Map<Integer, Integer> expected = Map.of(1, 2, 2, 2);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Тест: Частотный словарь для смешанных объектов")
    void testFrequencyDictionaryForMixedObjects() {
        List<Object> mixedList = List.of("a", 1, "bb", 1, "a", "bb");
        Map<Object, Integer> result = Task3.freqDict(mixedList);
        Map<Object, Integer> expected = Map.of("a", 2, 1, 2, "bb", 2);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Тест: Частотный словарь для пустого списка")
    void testFrequencyDictionaryForEmptyList() {
        List<Object> emptyList = List.of();
        Map<Object, Integer> result = Task3.freqDict(emptyList);
        Map<Object, Integer> expected = Map.of();
        assertEquals(expected, result);
    }
}
