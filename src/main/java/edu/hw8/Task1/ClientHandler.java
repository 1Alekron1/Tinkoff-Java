package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ClientHandler implements Runnable {
    public static final int MAX_BUFFER = 1024;
    private final static Logger LOGGER = LogManager.getLogger();
    private Socket clientSocket;

    ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override public void run() {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            byte[] buffer = new byte[MAX_BUFFER];
            int bytesRead = inputStream.read(buffer);
            if (bytesRead > 0) {
                String request = new String(buffer, 0, bytesRead);
                String response = getResponse(request);
                outputStream.write(response.getBytes());
            } else {
                clientSocket.close();
            }
        } catch (IOException e) {
            LOGGER.info(e.getStackTrace());
        }
    }

    private String getResponse(String request) {
        String answer = "";
        switch (request.toLowerCase()) {
            case "личности":
                answer = "Не переходи на личности там, где их нет";
                break;
            case "оскорбления":
                answer = "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами";
                break;
            case "глупый":
                answer =
                    "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... "
                        + "Ты просто бог идиотизма.";
                break;
            case "интеллект":
                answer = "Чем ниже интеллект, тем громче оскорбления";
                break;
            default:
                answer = "Неизвестный запрос";
        }
        return answer;
    }
}