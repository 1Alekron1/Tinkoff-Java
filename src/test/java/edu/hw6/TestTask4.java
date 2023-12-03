package edu.hw6;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTask4 {

    private static final Path TEST_FILE_PATH = Paths.get("./output.txt");

    @BeforeEach
    void setUp() {
        try {
            Files.deleteIfExists(TEST_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(TEST_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Writing to file with OutputStream composition")
    void writingToFileWithOutputStreamComposition() {
        // Запускаем код, который мы хотим протестировать
        Task4.writeToFile(null);

        // Проверяем, что файл был создан
        assertThat(Files.exists(TEST_FILE_PATH)).isTrue();

        // Проверяем содержимое файла
        try (InputStream inputStream = Files.newInputStream(TEST_FILE_PATH);
             CheckedInputStream checkedInputStream = new CheckedInputStream(inputStream, new CRC32());
             BufferedInputStream bufferedInputStream = new BufferedInputStream(checkedInputStream);
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, "UTF-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line = bufferedReader.readLine();
            assertThat(line).isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
