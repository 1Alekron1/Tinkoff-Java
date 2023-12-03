package edu.hw6;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FileCloner Test")
class TestTask2 {

    private static final String TEST_DIRECTORY = "./test";
    private static final String ORIGINAL_FILE_NAME = "diskmap.txt";

    @BeforeEach
    void setUp() {
        // Создаем тестовую директорию и оригинальный файл
        try {
            Files.createDirectory(Paths.get(TEST_DIRECTORY));
            Files.createFile(Paths.get(TEST_DIRECTORY, ORIGINAL_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        // Удаляем тестовую директорию и все ее содержимое после всех тестов
        try {
            Files.walk(Paths.get(TEST_DIRECTORY))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Cloning a file should create a copy with the correct name")
    void cloningFileShouldCreateCopyWithCorrectName() {
        try {
            Task2.cloneFile(Paths.get(TEST_DIRECTORY, ORIGINAL_FILE_NAME));

            // Проверяем, что в тестовой директории появился файл с копией
            Path[] files = Files.list(Paths.get(TEST_DIRECTORY)).toArray(Path[]::new);
            assertThat(files).hasSize(2);
            assertThat(files).anySatisfy(path -> assertThat(path.getFileName().toString()).startsWith("diskmap"));
            assertThat(files).anySatisfy(path -> assertThat(path.getFileName().toString()).matches("diskmap — копия( \\(\\d+\\))?\\.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Cloning multiple files should create copies with correct names and indices")
    void cloningMultipleFilesShouldCreateCopiesWithCorrectNamesAndIndices() {
        try {
            // Создаем несколько копий оригинального файла
            for (int i = 0; i < 3; i++) {
                Task2.cloneFile(Paths.get(TEST_DIRECTORY, ORIGINAL_FILE_NAME));
            }

            // Проверяем, что в тестовой директории появились три файла с копиями
            Path[] files = Files.list(Paths.get(TEST_DIRECTORY)).toArray(Path[]::new);
            assertThat(files).hasSize(4);
            assertThat(files).anySatisfy(path -> assertThat(path.getFileName().toString()).startsWith("diskmap"));
            assertThat(files).filteredOn(path -> path.getFileName().toString().matches("diskmap — копия( \\(\\d+\\))?\\.txt")).hasSize(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
