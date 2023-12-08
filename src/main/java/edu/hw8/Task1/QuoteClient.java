package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuoteClient {

    public static final int PORT = 8080;
    private final static Logger LOGGER = LogManager.getLogger();
    public static final int MAX_BUFFER = 1024;

    private QuoteClient() {
    }

    public static void run() {
        try (Socket socket = new Socket("localhost", PORT);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            Scanner scanner = new Scanner(System.in);
            LOGGER.info("Введите ключевое слово: ");
            String keyword = scanner.nextLine();

            outputStream.write(keyword.getBytes());

            byte[] buffer = new byte[MAX_BUFFER];
            int bytesRead = inputStream.read(buffer);
            String response = new String(buffer, 0, bytesRead);

            LOGGER.info("Ответ сервера: " + response);

        } catch (IOException e) {
            LOGGER.info(e.getStackTrace());
        }
    }
}
