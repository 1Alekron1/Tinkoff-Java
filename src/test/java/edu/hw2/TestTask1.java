package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {

    @Test
    @DisplayName("Тест: Оценка константы (ожидается 5.0)")
    void testConstantEvaluateThatReturnedExpectedValue() {
        final double EXPECTED_VALUE = 5.0;
        Expr.Constant constant = new Expr.Constant(5.0);
        double result = constant.evaluate();
        assertEquals(EXPECTED_VALUE, result, 0.001);
    }

    @Test
    @DisplayName("Тест: Оценка отрицания (ожидается -5.0)")
    void testNegateEvaluateThatReturnedExpectedValue() {
        final double EXPECTED_VALUE = -5.0;
        Expr.Constant constant = new Expr.Constant(5.0);
        Expr.Negate negate = new Expr.Negate(constant);
        double result = negate.evaluate();
        assertEquals(EXPECTED_VALUE, result, 0.001);
    }

    @Test
    @DisplayName("Тест: Оценка возведения в степень (ожидается 8.0)")
    void testExponentEvaluateThatReturnedExpectedValue() {
        final double EXPECTED_VALUE = 8.0;
        Expr.Constant base = new Expr.Constant(2.0);
        Expr.Constant power = new Expr.Constant(3.0);
        Expr.Exponent exponent = new Expr.Exponent(base, power);
        double result = exponent.evaluate();
        assertEquals(EXPECTED_VALUE, result, 0.001);
    }

    @Test
    @DisplayName("Тест: Оценка сложения (ожидается 5.0)")
    void testAdditionEvaluateThatReturnedExpectedValue() {
        final double EXPECTED_VALUE = 5.0;
        Expr.Constant firstTerm = new Expr.Constant(3.0);
        Expr.Constant secondTerm = new Expr.Constant(2.0);
        Expr.Addition addition = new Expr.Addition(firstTerm, secondTerm);
        double result = addition.evaluate();
        assertEquals(EXPECTED_VALUE, result, 0.001);
    }

    @Test
    @DisplayName("Тест: Оценка умножения (ожидается 8.0)")
    void testMultiplicationEvaluateThatReturnedExpectedValue() {
        final double EXPECTED_VALUE = 8.0;
        Expr.Constant firstMultiplier = new Expr.Constant(4.0);
        Expr.Constant secondMultiplier = new Expr.Constant(2.0);
        Expr.Multiplication multiplication = new Expr.Multiplication(firstMultiplier, secondMultiplier);
        double result = multiplication.evaluate();
        assertEquals(EXPECTED_VALUE, result, 0.001);
    }

    @Test
    @DisplayName("Тест: Комбинированный тест для различных выражений (ожидается результат сложного выражения)")
    void testCombinedExpressionsThatReturnedExpectedValue() {
        final double EXPECTED_VALUE = (-(5.0) + Math.pow(3.0, 2.0)) * 5.0;
        Expr.Constant constant1 = new Expr.Constant(5.0);
        Expr.Constant constant2 = new Expr.Constant(3.0);
        Expr.Negate negate = new Expr.Negate(constant1);
        Expr.Exponent exponent = new Expr.Exponent(constant2, 2.0);
        Expr.Addition addition = new Expr.Addition(negate, exponent);
        Expr.Multiplication multiplication = new Expr.Multiplication(constant1, addition);
        double result = multiplication.evaluate();
        assertEquals(EXPECTED_VALUE, result, 0.001);
    }

    @Test
    @DisplayName("Тест: Комбинированный тест для других выражений (ожидается результат сложного выражения)")
    void testCombinedExpressions2ThatReturnedExpectedValue() {
        final double EXPECTED_VALUE = -Math.pow(7.0, 2.0) * (7.0 + 2.0);
        Expr.Constant constant1 = new Expr.Constant(7.0);
        Expr.Constant constant2 = new Expr.Constant(2.0);
        Expr.Exponent exponent = new Expr.Exponent(constant1, constant2);
        Expr.Negate negate = new Expr.Negate(exponent);
        Expr.Addition addition = new Expr.Addition(constant1, constant2);
        Expr.Multiplication multiplication = new Expr.Multiplication(negate, addition);
        double result = multiplication.evaluate();
        assertEquals(EXPECTED_VALUE, result, 0.001);
    }
}
