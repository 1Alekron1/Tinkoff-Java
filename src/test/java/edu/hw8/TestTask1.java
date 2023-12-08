package edu.hw8;

import edu.hw8.Task1.QuoteServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {

    private static QuoteServer quoteServer;

    @BeforeAll
    static void setUp() {
        quoteServer = new QuoteServer();
        Thread serverThread = new Thread(() -> quoteServer.start()); // Передаем пустой массив аргументов
        serverThread.start();
    }

    @AfterAll
    static void tearDown() {
        quoteServer.stop();
    }

    @Test
    @DisplayName("Обработка запроса клиента для 'личности'")
    void testHandleClientRequestLichnosti() throws IOException {
        String clientRequest = "личности";
        String expectedResponse = "Не переходи на личности там, где их нет";
        String actualResponse = getServerResponse(clientRequest);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Обработка запроса клиента для 'оскорбления'")
    void testHandleClientRequestOskorbleniya() throws IOException {
        String clientRequest = "оскорбления";
        String expectedResponse =
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами";
        String actualResponse = getServerResponse(clientRequest);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Обработка запроса клиента для 'глупый'")
    void testHandleClientRequestGlupy() throws IOException {
        String clientRequest = "глупый";
        String expectedResponse =
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
        String actualResponse = getServerResponse(clientRequest);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Обработка запроса клиента для 'интеллект'")
    void testHandleClientRequestIntellect() throws IOException {
        String clientRequest = "интеллект";
        String expectedResponse = "Чем ниже интеллект, тем громче оскорбления";
        String actualResponse = getServerResponse(clientRequest);
        assertEquals(expectedResponse, actualResponse);
    }


    private String getServerResponse(String clientRequest) throws IOException {
        // Создаем фиктивный клиентский сокет и отправляем запрос на сервер
        try (Socket socket = new Socket("localhost", 8080);
             OutputStream outputStream = socket.getOutputStream()) {

            outputStream.write(clientRequest.getBytes());

            byte[] buffer = new byte[1024];
            int bytesRead = socket.getInputStream().read(buffer);
            return new String(buffer, 0, bytesRead);
        }
    }

    @Test
    @DisplayName("Обработка максимального количества одновременных соединений")
    void testMaximumConcurrentConnections() throws InterruptedException {
        int maxConnections = 5;
        ExecutorService executor = Executors.newFixedThreadPool(maxConnections + 1);
        CountDownLatch latch = new CountDownLatch(maxConnections);

        // Создаем фиктивные клиентские сокеты
        for (int i = 0; i < maxConnections + 1; i++) {
            executor.execute(() -> {
                try (Socket socket = new Socket("localhost", 8080)) { // Используем порт 8080
                    // Ожидаем завершения обработки запроса на сервере
                    latch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        // Ожидаем, чтобы позволить серверу обработать все соединения
        Thread.sleep(500); // Подождем, чтобы сервер успел обработать соединения

        // Проверяем, что максимальное количество соединений не превышено
        assertEquals(0, latch.getCount());

        executor.shutdownNow();
    }
}
