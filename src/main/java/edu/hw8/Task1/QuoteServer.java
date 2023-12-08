package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuoteServer {

    public static final int N_THREADS = 5;
    public static final int PORT = 8080;
    private final static Logger LOGGER = LogManager.getLogger();
    private ExecutorService executor;

    public void start() {
        executor = Executors.newFixedThreadPool(N_THREADS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.info("Сервер запущен. Ожидание подключений...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            LOGGER.info(e.getStackTrace());
        }
    }

    public void stop() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}

