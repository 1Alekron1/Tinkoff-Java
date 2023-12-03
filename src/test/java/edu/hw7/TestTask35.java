package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.Task35;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask35 {

    @Test
    @DisplayName(
        "Given: База данных с несколькими записями, When: Параллельное добавление, Then: Все записи доступны для поиска")
    public void givenDatabaseWithMultipleRecords_whenParallelAddition_thenAllRecordsAreAccessible()
        throws InterruptedException {
        // Given
        PersonDatabase database = new Task35();
        int numberOfThreads = 10;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // When
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                Person person = new Person(Thread.currentThread().hashCode(), "John", "123 Main St", "555-1234");
                database.add(person);
                latch.countDown();
            });
        }

        latch.await();
        List<Person> result = database.findByName("John");

        // Then
        assertEquals(numberOfThreads, result.size());
    }

    @Test
    @DisplayName(
        "Given: База данных с записями, When: Параллельное добавление и удаление, Then: Удаленные записи недоступны для поиска")
    public void givenDatabaseWithRecords_whenParallelAdditionAndDeletion_thenDeletedRecordsAreNotAccessible()
        throws InterruptedException {
        // Given
        PersonDatabase database = new Task35();
        int numberOfThreads = 10;
        CountDownLatch addLatch = new CountDownLatch(numberOfThreads);
        CountDownLatch deleteLatch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // When
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                Person person = new Person(Thread.currentThread().hashCode(), "Alice", "456 Oak St", "555-5678");
                database.add(person);
                addLatch.countDown();

                try {
                    // Ждем, пока все потоки завершат добавление
                    addLatch.await();

                    // Теперь удаляем запись
                    database.delete(person.id());
                    deleteLatch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        deleteLatch.await();

        // Then
        List<Person> result = database.findByAddress("456 Oak St");
        assertEquals(0, result.size());
    }
}
