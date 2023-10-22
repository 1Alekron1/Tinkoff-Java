package edu.hw2;

import edu.hw2.task2.Square;
import edu.hw2.task2.Rectangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {

    @Test
    @DisplayName("Тест: Площадь прямоугольника (ожидается 12)")
    void testRectangleAreaThatReturnedExpectedValue() {
        final double EXPECTED_AREA = 12;
        Rectangle rectangle = new Rectangle(4, 3);
        double area = rectangle.area();
        assertEquals(EXPECTED_AREA, area);
    }

    @Test
    @DisplayName("Тест: Площадь квадрата (ожидается 16)")
    void testSquareAreaThatReturnedExpectedValue() {
        final double EXPECTED_AREA = 16;
        Square square = new Square(4);
        double area = square.area();
        assertEquals(EXPECTED_AREA, area);
    }

    @Test
    @DisplayName("Тест: Изменение ширины квадрата и проверка его площади (ожидается 20)")
    void testSquareToRectangleChangeWidthThatReturnedExpectedValue() {
        final double EXPECTED_AREA = 20;
        Square square = new Square(4);
        Rectangle rectangle = square.setWidth(5);
        double area = rectangle.area();
        assertEquals(EXPECTED_AREA, area);
    }

    @Test
    @DisplayName("Тест: Изменение высоты квадрата и проверка его площади (ожидается 20)")
    void testSquareToRectangleChangeHeightThatReturnedExpectedValue() {
        final double EXPECTED_AREA = 20;
        Square square = new Square(4);
        Rectangle rectangle = square.setHeight(5);
        double area = rectangle.area();
        assertEquals(EXPECTED_AREA, area);
    }

    @Test
    @DisplayName("Тест: Переход прямоугольника в квадрат (ожидается 16)")
    void testRectangleToSquareThatReturnedExpectedValue() {
        final double EXPECTED_AREA = 16;
        Rectangle rectangle = new Rectangle(4, 6);
        rectangle = rectangle.setWidth(4);
        rectangle = rectangle.setHeight(4);
        double area = rectangle.area();
        assertEquals(EXPECTED_AREA, area);
    }
}
