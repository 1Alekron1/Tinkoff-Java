package edu.hw8;

import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTask2 {

    private ThreadPool threadPool;

    @BeforeEach
    void setUp() {
        threadPool = new FixedThreadPool(7);
    }

    @AfterEach
    void tearDown() throws Exception {
        threadPool.close();
    }

    @Test
    @DisplayName("Дан пул потоков при запуске, когда выполняются задачи, тогда задачи должны быть завершены")
    void givenThreadPool_whenExecutingTasks_thenTasksCompleted() throws Exception {
        // Arrange
        threadPool.start();
        final int[] results = new int[10];

        // Act
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            threadPool.execute(() -> results[finalI] = finalI);
        }

        // Assert
        threadPool.close();
        for (int i = 0; i < 10; i++) {
            assertEquals(i, results[i]);
        }
    }

    @Test
    @DisplayName(
        "Дан пул потоков при запуске, когда задачи вычисляют числа Фибоначчи, тогда должны получить правильные результаты")
    void givenThreadPool_whenTasksComputeFibonacci_thenCorrectResults() throws Exception {
        // Arrange
        threadPool.start();
        final long[] results = new long[10];

        // Act
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            threadPool.execute(() -> results[finalI] = FixedThreadPool.fibonacci(finalI));
        }

        // Assert
        threadPool.close();
        assertEquals(0, results[0]);
        assertEquals(1, results[1]);
        assertEquals(1, results[2]);
        assertEquals(2, results[3]);
        assertEquals(3, results[4]);
        assertEquals(5, results[5]);
        assertEquals(8, results[6]);
        assertEquals(13, results[7]);
        assertEquals(21, results[8]);
    }
}
