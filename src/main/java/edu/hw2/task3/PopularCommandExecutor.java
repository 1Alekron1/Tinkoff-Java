package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.manager.ConnectionManager;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt upgrade -y");
    }

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    void tryExecute(String command) throws Exception {
        Exception exception = null;
        for (var i = 0; i < this.maxAttempts; i++) {
            Connection connection = manager.getConnection();
            try (connection) {
                connection.execute(command);
                return;
            } catch (Exception e) {
                exception = e;
            }
        }
        throw new Exception("превышено количество попыток", exception);
    }

}
