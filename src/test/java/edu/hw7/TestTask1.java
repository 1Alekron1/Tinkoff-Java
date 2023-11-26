package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask1 {

    @Test
    @DisplayName("Инкремент в одном потоке: ожидаемое значение и использование AtomicInteger")
    public void givenSingleThread_whenIncrementCounter_thenExpectedValueAndAtomic() {
        // Given
        final int numberOfThreads = 1;

        // When
        AtomicInteger result = new Task1().getCounter(numberOfThreads);

        // Then
        assertEquals(10000, result.get());
        assertTrue(Task1.isAtomicInteger(result));
    }

    @Test
    @DisplayName("Инкремент в нескольких потоках: ожидаемое значение и использование AtomicInteger")
    public void givenMultipleThreads_whenIncrementCounter_thenExpectedValueAndAtomic() {
        // Given
        final int numberOfThreads = 3;

        // When
        AtomicInteger result = new Task1().getCounter(numberOfThreads);

        // Then
        assertEquals(30000, result.get());
        assertTrue(Task1.isAtomicInteger(result));
    }

    @Test
    @DisplayName("Инкремент во многих потоках: ожидаемое значение и использование AtomicInteger")
    public void givenManyThreads_whenIncrementCounter_thenExpectedValueAndAtomic() {
        // Given
        final int numberOfThreads = 10;

        // When
        AtomicInteger result = new Task1().getCounter(numberOfThreads);

        // Then
        assertEquals(100000, result.get());
        assertTrue(Task1.isAtomicInteger(result));
    }

    @Test
    @DisplayName("Отсутствие состояния гонки: инкремент в нескольких потоках")
    public void givenNoRaceCondition_whenMultipleThreadsIncrement_thenExpectedValue() throws InterruptedException {
        // Given
        final int numberOfThreads = 5;
        AtomicInteger result = new AtomicInteger(0);

        // When
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 2000; j++) {
                    result.incrementAndGet();
                }
            });
            threads[i].start();
        }

        // Then
        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(10000, result.get());
    }
}
