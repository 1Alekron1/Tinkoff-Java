package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTask2 {

    @Test
    @DisplayName("Given: Положительное число, When: Вычисление факториала, Then: Ожидаемый результат")
    public void givenPositiveNumber_whenCalculateFactorial_thenExpectedResult() {
        // Given
        int number = 5;

        // When
        BigInteger result = Task2.calculateFactorial(number);

        // Then
        assertEquals(BigInteger.valueOf(120), result);
    }

    @Test
    @DisplayName("Given: Ноль, When: Вычисление факториала, Then: Ожидаемый результат (1)")
    public void givenZero_whenCalculateFactorial_thenExpectedResult() {
        // Given
        int number = 0;

        // When
        BigInteger result = Task2.calculateFactorial(number);

        // Then
        assertEquals(BigInteger.ONE, result);
    }

    @Test
    @DisplayName("Given: Отрицательное число, When: Вычисление факториала, Then: Исключение IllegalArgumentException")
    public void givenNegativeNumber_whenCalculateFactorial_thenIllegalArgumentException() {
        // Given
        int number = -5;

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> Task2.calculateFactorial(number));
    }

    @Test
    @DisplayName("Given: Большое число, When: Вычисление факториала, Then: Ожидаемый результат")
    public void givenLargeNumber_whenCalculateFactorial_thenExpectedResult() {
        // Given
        int number = 20;

        // When
        BigInteger result = Task2.calculateFactorial(number);

        // Then
        assertEquals(new BigInteger("2432902008176640000"), result);
    }
}
