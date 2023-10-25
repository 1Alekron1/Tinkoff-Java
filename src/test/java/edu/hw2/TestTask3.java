package edu.hw2;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.implementation.FaultyConnection;
import edu.hw2.task3.manager.ConnectionManager;
import edu.hw2.task3.manager.implementation.DefaultConnectionManager;
import edu.hw2.task3.manager.implementation.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTask3 {

    @Test
    @DisplayName("Тест: FaultyConnectionManager возвращает FaultyConnection")
    void testUseFaultyConnectionManager() {
        var manager = new FaultyConnectionManager();
        Connection connection = manager.getConnection();

        assertInstanceOf(FaultyConnection.class, connection);
    }

    @Test
    @DisplayName("Тест: Проверка вероятности получения FaultyConnection (0.5 вероятность)")
    void testFaultyConnectionProbabilityThatReturnedTrue() {
        final int TOTAL_ITERATIONS = 10000;
        int faultyConnectionCount = 0;

        final double EXPECTED_PROBABILITY = 0.5;
        final double TOLERANCE = 0.05;

        ConnectionManager manager = new DefaultConnectionManager();

        for (int i = 0; i < TOTAL_ITERATIONS; i++) {
            Connection connection = manager.getConnection();
            if (connection instanceof FaultyConnection) {
                faultyConnectionCount++;
            }
        }

        double probability = (double) faultyConnectionCount / TOTAL_ITERATIONS;

        assert Math.abs(probability - EXPECTED_PROBABILITY) < TOLERANCE : "Вероятность не соответствует ожидаемой.";
    }
}
