package edu.hw9;

import edu.hw9.Task3.DepthFirstSearch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask3 {

    // Наверное можно создать файловую структуру проще, но я не знаю как
    private static final String TEST_DIRECTORY_PATH = "test_directory";
    private static final String DIR1_PATH = TEST_DIRECTORY_PATH + "/dir1";
    private static final String FILE1_PATH = DIR1_PATH + "/file1.txt";
    private static final String FILE2_PATH = DIR1_PATH + "/file2.txt";
    private static final String DIR2_PATH = DIR1_PATH + "/dir2";
    private static final String FILE3_PATH = DIR2_PATH + "/file3.txt";
    private static final String FILE4_PATH = DIR2_PATH + "/file4.txt";
    private static final String DIR3_PATH = DIR2_PATH + "/dir3";
    private static final String FILE5_PATH = DIR3_PATH + "/file5.txt";
    private static final String FILE6_PATH = DIR3_PATH + "/file6.txt";
    private static final String DIR4_PATH = DIR3_PATH + "/dir4";
    private static final String FILE7_PATH = DIR4_PATH + "/file7.txt";
    private static final String FILE8_PATH = DIR4_PATH + "/file8.txt";
    private static final String DIR5_PATH = TEST_DIRECTORY_PATH + "/dir5";
    private static final String FILE9_PATH = DIR5_PATH + "/file9.txt";
    private static final String FILE10_PATH = DIR5_PATH + "/file10.txt";
    private static final String DIR6_PATH = DIR5_PATH + "/dir6";
    private static final String FILE11_PATH = DIR6_PATH + "/file11.txt";
    private static final String FILE12_PATH = DIR6_PATH + "/file12.txt";
    private static final String DIR7_PATH = DIR6_PATH + "/dir7";
    private static final String FILE13_PATH = DIR7_PATH + "/file13.txt";
    private static final String FILE14_PATH = DIR7_PATH + "/file14.txt";
    private static final String DIR8_PATH = DIR7_PATH + "/dir8";
    private static final String FILE15_PATH = DIR8_PATH + "/file15.txt";
    private static final String FILE16_PATH = DIR8_PATH + "/file16.txt";

    @BeforeAll
    @DisplayName("Настройка тестовой структуры каталога")
    static void setUp() throws IOException {
        createTestDirectoryStructure();
        createTestFiles();
    }

    @AfterAll
    @DisplayName("Удаление тестовой структуры каталога")
    static void tearDown() throws IOException {
        deleteTestDirectory(new File(TEST_DIRECTORY_PATH));
    }

    private static void deleteTestDirectory(File directory) throws IOException {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteTestDirectory(file);
                    } else {
                        Files.deleteIfExists(file.toPath());
                    }
                }
            }
            Files.deleteIfExists(directory.toPath());
        }
    }

    private static void createTestDirectoryStructure() throws IOException {
        Files.createDirectories(Path.of(TEST_DIRECTORY_PATH));
        Files.createDirectories(Path.of(DIR1_PATH));
        Files.createDirectories(Path.of(DIR2_PATH));
        Files.createDirectories(Path.of(DIR3_PATH));
        Files.createDirectories(Path.of(DIR4_PATH));
        Files.createDirectories(Path.of(DIR5_PATH));
        Files.createDirectories(Path.of(DIR6_PATH));
        Files.createDirectories(Path.of(DIR7_PATH));
        Files.createDirectories(Path.of(DIR8_PATH));
    }

    private static void createTestFiles() throws IOException {
        Files.write(Path.of(FILE1_PATH), "Тестовое содержимое 1".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE2_PATH), "Тестовое содержимое 2".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE3_PATH), "Тестовое содержимое 3".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE4_PATH), "Тестовое содержимое 4".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE5_PATH), "Тестовое содержимое 5".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE6_PATH), "Тестовое содержимое 6".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE7_PATH), "Тестовое содержимое 7".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE8_PATH), "Тестовое содержимое 8".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE9_PATH), "Тестовое содержимое 9".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE10_PATH), "Тестовое содержимое 10".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE11_PATH), "Тестовое содержимое 11".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE12_PATH), "Тестовое содержимое 12".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE13_PATH), "Тестовое содержимое 13".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE14_PATH), "Тестовое содержимое 14".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE15_PATH), "Тестовое содержимое 15".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE16_PATH), "Тестовое содержимое 16".getBytes(), StandardOpenOption.CREATE);
    }

    @Test
    @DisplayName("При поиске файла в глубину, файл должен быть найден")
    void whenSearchFileInDepth_thenFileFound() throws Exception {
        DepthFirstSearch dfs = new DepthFirstSearch(new File(TEST_DIRECTORY_PATH), "file16.txt");
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Тест 1: Поиск файла в глубину, файл должен быть найден");
        boolean result = pool.invoke(dfs);;

        assertTrue(result, "Ожидается, что файл будет найден");
    }

    @Test
    @DisplayName("При поиске несуществующего файла в глубину, ничего не должно быть найдено")
    void whenSearchNonExistingFileInDepth_thenNoFileFound() throws Exception {
        DepthFirstSearch dfs = new DepthFirstSearch(new File(TEST_DIRECTORY_PATH), "nonexistent.txt");
        ForkJoinPool pool = new ForkJoinPool();
        boolean result = pool.invoke(dfs);

        assertFalse(result, "Ожидается, что файл не будет найден");
    }
}
