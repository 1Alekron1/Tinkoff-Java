package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTask4 {

    @Test
    @DisplayName(
        "Given: Калькулятор Monte Carlo Pi, When: Однопоточное вычисление числа Пи, Then: Проверка диапазона значений")
    public void givenMonteCarloPiCalculator_whenCalculatingPiSingleThread_thenCheckRange() {
        // When
        double pi = Task4.calculatePiSingleThread(1000000);

        // Then
        assertTrue(pi > 2.5 && pi < 4.0, "Значение Пи должно быть в пределах от 2.5 до 4.0");
    }

    @Test
    @DisplayName(
        "Given: Калькулятор Monte Carlo Pi, When: Многопоточное вычисление числа Пи, Then: Проверка диапазона значений")
    public void givenMonteCarloPiCalculator_whenCalculatingPiMultiThread_thenCheckRange() {
        // When
        double pi = Task4.calculatePiMultiThread(1000000, 4);

        // Then
        assertTrue(pi > 2.5 && pi < 4.0, "Значение Пи должно быть в пределах от 2.5 до 4.0");
    }

    @Test
    @DisplayName(
        "Given: Калькулятор Monte Carlo Pi, When: Сравнение результатов однопоточной и многопоточной версий, Then: Проверка равенства результатов")
    public void givenMonteCarloPiCalculator_whenComparingSingleAndMultiThreadResults_thenCheckEquality() {
        // When
        double piSingleThread = Task4.calculatePiSingleThread(1000000);
        double piMultiThread = Task4.calculatePiMultiThread(1000000, 4);

        // Then
        assertEquals(
            piSingleThread,
            piMultiThread,
            0.01,
            "Результаты однопоточной и многопоточной версий должны быть примерно равны"
        );
    }

    @Test
    @DisplayName("Given: Калькулятор Monte Carlo Pi, When: Вычисление погрешности, Then: Проверка погрешности")
    public void givenMonteCarloPiCalculator_whenCalculatingError_thenCheckError() {
        // When
        double piSingleThread = Task4.calculatePiSingleThread(1000000);
        double piMultiThread = Task4.calculatePiMultiThread(1000000, 4);

        // Then
        double errorSingleThread = Math.abs(piSingleThread - Math.PI);
        double errorMultiThread = Math.abs(piMultiThread - Math.PI);

        assertTrue(errorSingleThread < 0.01, "Погрешность однопоточной версии должна быть менее 0.01");
        assertTrue(errorMultiThread < 0.01, "Погрешность многопоточной версии должна быть менее 0.01");
    }

    @Test
    @DisplayName("Given: Калькулятор Monte Carlo Pi, When: Вычисление времени выполнения, Then: Проверка результатов и ускорения")
    public void givenMonteCarloPiCalculator_whenComparingExecutionTime_thenResultsAndSpeedupMatch() {
        int simulations = 10000000;

        // When
        long startTimeSingleThread = System.currentTimeMillis();
        double piSingleThread = Task4.calculatePiSingleThread(simulations);
        long endTimeSingleThread = System.currentTimeMillis();
        long executionTimeSingleThread = endTimeSingleThread - startTimeSingleThread;

        long startTimeMultiThread = System.currentTimeMillis();
        double piMultiThread = Task4.calculatePiMultiThread(simulations, 4);
        long endTimeMultiThread = System.currentTimeMillis();
        long executionTimeMultiThread = endTimeMultiThread - startTimeMultiThread;

        // Then
        assertEquals(piSingleThread, piMultiThread, 0.01);
        double speedup = (double) executionTimeSingleThread / executionTimeMultiThread;
        System.out.println("Ускорение: " + speedup);
    }
}
