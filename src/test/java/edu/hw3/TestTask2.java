package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestTask2 {

    @Test
    @DisplayName("Тест: Кластеризация строк с одной парой скобок")
    void testClusterizeSinglePair() {
        String input = "(World)";
        String[] expected = {"(World)"};
        assertArrayEquals(expected, Task2.clusterize(input));
    }

    @Test
    @DisplayName("Тест: Кластеризация строк с двумя парами скобок")
    void testClusterizeTwoPairs() {
        String input = "(is a)(test)";
        String[] expected = {"(is a)", "(test)"};
        assertArrayEquals(expected, Task2.clusterize(input));
    }

    @Test
    @DisplayName("Тест: Кластеризация пустой строки")
    void testClusterizeEmptyString() {
        String input = "";
        String[] expected = {};
        assertArrayEquals(expected, Task2.clusterize(input));
    }


    @Test
    @DisplayName("Тест: Кластеризация строки с незакрытой скобкой")
    void testClusterizeStringWithUnclosedBracket() {
        String input = "( bracket (not closed";
        String[] expected = {};
        assertArrayEquals(expected, Task2.clusterize(input));
    }

    @Test
    @DisplayName("Тест: Кластеризация строки с несколькими вложенными парами скобок")
    void testClusterizeNestedPairs() {
        String input = "((())())(()(()()))";
        String[] expected = {"((())())", "(()(()()))"};
        assertArrayEquals(expected, Task2.clusterize(input));
    }

}
