package edu.hw2.task3.connection.implementation;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.exception.ConnectionException;

public class FaultyConnection implements Connection {

    public static final int RANDOM_BOUNDARIES_SIZE = 2;
    public static final int FAULT_NUMBER = 1;

    @Override
    public void execute(String command) {
        int randomState = (int) (Math.random() * RANDOM_BOUNDARIES_SIZE);
        if (randomState == FAULT_NUMBER) {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
