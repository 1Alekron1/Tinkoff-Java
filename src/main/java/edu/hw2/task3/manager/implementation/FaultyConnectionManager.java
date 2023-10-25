package edu.hw2.task3.manager.implementation;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.implementation.FaultyConnection;
import edu.hw2.task3.manager.ConnectionManager;

public class FaultyConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        return new FaultyConnection();
    }
}
