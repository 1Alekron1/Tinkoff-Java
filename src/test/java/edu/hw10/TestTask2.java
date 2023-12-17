package edu.hw10;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.Task2.Calculator;
import edu.hw10.Task2.FactorialCalculator;
import edu.hw10.Task2.FibCalculator;
import edu.hw10.Task2.SimpleFibCalculator;
import org.junit.jupiter.api.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class TestTask2 {

    private static final String CACHE_DIRECTORY = "test_cache";

    private FibCalculator calculator;
    private FibCalculator cachedCalculator;
    private Calculator calculator1;
    private Calculator cachedCalculator1;

    @BeforeEach
    void setUp() {
        calculator = new SimpleFibCalculator();
        cachedCalculator = CacheProxy.create(calculator, FibCalculator.class, CACHE_DIRECTORY);
        calculator1 = new FactorialCalculator();
        cachedCalculator1 = CacheProxy.create(calculator1, Calculator.class, CACHE_DIRECTORY);
    }

    @AfterEach
    void tearDown() {
        // Clean up the cache directory after each test
        deleteCacheDirectory(new File(CACHE_DIRECTORY));
    }

    @Test
    @DisplayName("Для класса с аннотацией @Cache, при генерации объекта, все поля должны быть не пустыми")
    void givenClassWithCacheAnnotation_whenGeneratingObject_thenAllFieldsNonNull() throws Exception {
        long result = cachedCalculator.fib(10);
        assertNotNull(result);
    }

    @Test
    @DisplayName("For a class with @Cache annotation, when generating an object, all fields should be non-null")
    void givenCalculatorClassWithCacheAnnotation_whenGeneratingObject_thenAllFieldsNonNull() throws Exception {
        int result = cachedCalculator1.calculate(5);
        assertEquals(120, result);
    }

    @Test
    @DisplayName("For a class with @Cache annotation, results should be persisted on disk")
    void givenCalculatorClassWithCacheAnnotation_whenGeneratingObject_thenResultsPersistedOnDisk() throws Exception {
        // Perform multiple invocations to populate the cache
        cachedCalculator1.calculate(5);
        cachedCalculator1.calculate(7);
        cachedCalculator1.calculate(10);

        // Verify that cache files exist
        assertTrue(cacheFileExists("calculate_5"));
        assertTrue(cacheFileExists("calculate_7"));
        assertTrue(cacheFileExists("calculate_10"));
    }

    @Test
    @DisplayName("When regenerating object with @Cache annotation, results should be taken from the cache")
    void givenCalculatorClassWithCacheAnnotation_whenRegeneratingObject_thenResultsTakenFromCache() throws Exception {
        // Perform the first invocation to populate the cache
        cachedCalculator1.calculate(8);

        // Measure time for the second invocation
        long startTime = System.currentTimeMillis();
        int result = cachedCalculator1.calculate(8);
        long endTime = System.currentTimeMillis();

        assertTrue(endTime - startTime < 10); // Ensure that the second invocation is fast (from cache)
        assertEquals(40320, result);
    }

    @Test
    @DisplayName("Для класса с аннотацией @Cache, результаты должны сохраняться на диск")
    void givenClassWithCacheAnnotation_whenGeneratingObject_thenResultsPersistedOnDisk() throws Exception {
        // Perform multiple invocations to populate the cache
        cachedCalculator.fib(10);
        cachedCalculator.fib(20);
        cachedCalculator.fib(30);

        // Verify that cache files exist
        assertTrue(cacheFileExists("fib_10"));
        assertTrue(cacheFileExists("fib_20"));
        assertTrue(cacheFileExists("fib_30"));
    }

    @Test
    @DisplayName("При повторной генерации объекта с аннотацией @Cache, результаты должны браться из кэша")
    void givenClassWithCacheAnnotation_whenRegeneratingObject_thenResultsTakenFromCache() throws Exception {
        // Perform the first invocation to populate the cache
        cachedCalculator.fib(15);

        // Measure time for the second invocation
        long startTime = System.currentTimeMillis();
        long result = cachedCalculator.fib(15);
        long endTime = System.currentTimeMillis();

        assertTrue(endTime - startTime < 10); // Ensure that the second invocation is fast (from cache)
    }

    private boolean cacheFileExists(String key) {
        String filePath = CACHE_DIRECTORY + File.separator + key;
        return Files.exists(Paths.get(filePath));
    }

    private void deleteCacheDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteCacheDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
}
