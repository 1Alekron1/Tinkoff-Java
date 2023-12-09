package edu.hw9;

import edu.hw9.Task2.DirectoryCountTask;
import edu.hw9.Task2.FileSearchTask;
import edu.hw9.Task2.FileSystemNode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask2 {

    private static final String TEST_DIRECTORY_PATH = "test_directory";
    private static final String SUBDIRECTORY_PATH = TEST_DIRECTORY_PATH + "/subdirectory";
    private static final String FILE1_PATH = TEST_DIRECTORY_PATH + "/file1.txt";
    private static final String FILE2_PATH = SUBDIRECTORY_PATH + "/file2.txt";
    private static final String FILE3_PATH = TEST_DIRECTORY_PATH + "/file3.docx";
    private static final String LARGE_DIR_PATH = TEST_DIRECTORY_PATH + "/large_directory";

    @BeforeAll
    @DisplayName("Настройка тестовой структуры каталога")
    static void setUp() throws IOException {
        createTestDirectoryStructure();
        createTestFiles();
        createFilesForLargeDirectory();
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
        Files.createDirectories(Path.of(SUBDIRECTORY_PATH));
        Files.createDirectories(Path.of(LARGE_DIR_PATH));
    }

    private static void createTestFiles() throws IOException {
        Files.write(Path.of(FILE1_PATH), "Тестовое содержимое 1".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE2_PATH), "Тестовое содержимое 2".getBytes(), StandardOpenOption.CREATE);
        Files.write(Path.of(FILE3_PATH), "Тестовое содержимое 3".getBytes(), StandardOpenOption.CREATE);
    }

    private static void createFilesForLargeDirectory() throws IOException {
        IntStream.rangeClosed(1, 1001)
            .forEach(i -> {
                try {
                    Files.write(Path.of(LARGE_DIR_PATH, "file" + i + ".txt"),
                        ("Тестовое содержимое " + i).getBytes(),
                        StandardOpenOption.CREATE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }

    @Test
    @DisplayName("Поиск директорий с более чем 1000 файлами")
    void whenSearchDirectoriesWithMoreThan1000Files_thenDirectoriesFound() {
        // Дано
        FileSystemNode root = new FileSystemNode(new File(TEST_DIRECTORY_PATH));
        DirectoryCountTask directoryCountTask = new DirectoryCountTask(root);
        ForkJoinPool pool = new ForkJoinPool();

        // Когда
        System.out.println("Тест: Поиск директорий с более чем 1000 файлами");
        List<File> directoriesWithMoreThan1000Files = pool.invoke(directoryCountTask);

        // Тогда
        assertEquals(1, directoriesWithMoreThan1000Files.size(),
            "Ожидается, что найдена 1 директория с более чем 1000 файлами");
        directoriesWithMoreThan1000Files.forEach(directory ->
            System.out.println("Директория с более чем 1000 файлами: " + directory.getAbsolutePath()));
    }

    @Test
    @DisplayName("При поиске файлов по расширению, должны быть найдены правильные файлы")
    void whenSearchFilesByExtension_thenCorrectFilesFound() {
        File rootDirectory = new File(TEST_DIRECTORY_PATH);
        FileSystemNode rootNode = new FileSystemNode(rootDirectory);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileSearchTask fileSearchTask = new FileSearchTask(rootNode, file -> file.getName().endsWith(".txt"));

        List<File> foundFiles = forkJoinPool.invoke(fileSearchTask);

        assertEquals(1003, foundFiles.size());
        assertTrue(foundFiles.contains(new File(FILE1_PATH)));
        assertTrue(foundFiles.contains(new File(FILE2_PATH)));
    }

    @Test
    @DisplayName("При поиске файлов по размеру, должны быть найдены правильные файлы")
    void whenSearchFilesBySize_thenCorrectFilesFound() {
        File rootDirectory = new File(TEST_DIRECTORY_PATH);
        FileSystemNode rootNode = new FileSystemNode(rootDirectory);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileSearchTask fileSearchTask = new FileSearchTask(rootNode, file -> file.length() > 0);

        List<File> foundFiles = forkJoinPool.invoke(fileSearchTask);

        assertEquals(1004, foundFiles.size());
        assertTrue(foundFiles.contains(new File(FILE1_PATH)));
        assertTrue(foundFiles.contains(new File(FILE2_PATH)));
        assertTrue(foundFiles.contains(new File(FILE3_PATH)));
    }
}
