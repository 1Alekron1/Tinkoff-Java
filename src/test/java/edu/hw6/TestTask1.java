package edu.hw6;

import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DiskMap Test")
class TestTask1 {

    private static final String TEST_FILE_PATH = "test_diskmap.txt";

    @BeforeEach
    void setUp() {
        // Убедимся, что файл не существует перед каждым тестом
        deleteTestFileIfExists();
    }

    @AfterEach
    void tearDown() {
        // Убедимся, что файл удаляется после каждого теста
        deleteTestFileIfExists();
    }

    @Test
    @DisplayName("Newly created DiskMap should be empty")
    void newlyCreatedDiskMapShouldBeEmpty() {
        Task1 diskMap = new Task1(TEST_FILE_PATH);
        assertThat(diskMap).isEmpty();
    }

    @Test
    @DisplayName("Putting a key-value pair should store it in memory and on disk")
    void puttingKeyValuePairShouldStoreInMemoryAndOnDisk() {
        Task1 diskMap = new Task1(TEST_FILE_PATH);
        diskMap.put("key1", "value1");

        // Проверка в памяти
        assertThat(diskMap).hasSize(1).containsEntry("key1", "value1");

        // Проверка на диске
        assertFileContents(TEST_FILE_PATH, "key1:value1");
    }

    @Test
    @DisplayName("Removing a key should remove it from memory and on disk")
    void removingKeyShouldRemoveFromMemoryAndOnDisk() {
        Task1 diskMap = new Task1(TEST_FILE_PATH);
        diskMap.put("key1", "value1");

        // Удаление ключа
        diskMap.remove("key1");

        // Проверка в памяти
        assertThat(diskMap).isEmpty();

        // Проверка на диске
        assertFileContents(TEST_FILE_PATH);
    }

    @Test
    @DisplayName("Clearing the map should remove all entries from memory and on disk")
    void clearingMapShouldRemoveAllEntriesFromMemoryAndOnDisk() {
        Task1 diskMap = new Task1(TEST_FILE_PATH);
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // Очистка карты
        diskMap.clear();

        // Проверка в памяти
        assertThat(diskMap).isEmpty();

        // Проверка на диске
        assertFileContents(TEST_FILE_PATH);
    }

    @Test
    @DisplayName("Loading from disk should update the in-memory map")
    void loadingFromDiskShouldUpdateInMemoryMap() {
        // Записываем данные в файл напрямую
        writeToFile(TEST_FILE_PATH, "key1:value1\nkey2:value2");

        // Создаем DiskMap и загружаем данные из файла
        Task1 diskMap = new Task1(TEST_FILE_PATH);
        diskMap.loadFromDisk();

        // Проверка в памяти
        assertThat(diskMap).hasSize(2).containsEntry("key1", "value1").containsEntry("key2", "value2");
    }

    private void assertFileContents(String filePath, String... expectedLines) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (String expectedLine : expectedLines) {
                assertThat(reader.readLine()).isEqualTo(expectedLine);
            }
            // Проверяем, что на файле не осталось лишних строк
            assertThat(reader.readLine()).isNull();
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    private void writeToFile(String filePath, String content) {
        try {
            Files.write(Path.of(filePath), content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + filePath, e);
        }
    }

    private void deleteTestFileIfExists() {
        try {
            Files.deleteIfExists(Path.of(TEST_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
