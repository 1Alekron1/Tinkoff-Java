package edu.hw2.task3.manager.implementation;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.implementation.FaultyConnection;
import edu.hw2.task3.connection.implementation.StableConnection;
import edu.hw2.task3.manager.ConnectionManager;

public class DefaultConnectionManager implements ConnectionManager {

    public static final int RANDOM_BOUNDARIES_SIZE = 2;
    public static final int FAULT_NUMBER = 1;

    @Override
    public Connection getConnection() {
        int randomState = (int) (Math.random() * RANDOM_BOUNDARIES_SIZE);
        if (randomState == FAULT_NUMBER) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
