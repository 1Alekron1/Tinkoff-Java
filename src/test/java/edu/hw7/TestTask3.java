package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask3 {

    @Test
    @DisplayName("Добавление и поиск человека по имени")
    public void givenPersonDatabase_whenAddPerson_thenFindByName() {
        // Given
        PersonDatabase database = new Task3();
        Person person = new Person(1, "John", "123 Main St", "555-1234");

        // When
        database.add(person);
        List<Person> result = database.findByName("John");

        // Then
        assertEquals(List.of(person), result);
    }

    @Test
    @DisplayName("Добавление и поиск человека по адресу")
    public void givenPersonDatabase_whenAddPerson_thenFindByAddress() {
        // Given
        PersonDatabase database = new Task3();
        Person person = new Person(1, "Alice", "456 Oak St", "555-5678");

        // When
        database.add(person);
        List<Person> result = database.findByAddress("456 Oak St");

        // Then
        assertEquals(List.of(person), result);
    }

    @Test
    @DisplayName("Добавление и поиск человека по номеру телефона")
    public void givenPersonDatabase_whenAddPerson_thenFindByPhone() {
        // Given
        PersonDatabase database = new Task3();
        Person person = new Person(1, "Bob", "789 Pine St", "555-9876");

        // When
        database.add(person);
        List<Person> result = database.findByPhone("555-9876");

        // Then
        assertEquals(List.of(person), result);
    }

    @Test
    @DisplayName("Удаление человека и проверка, что его нельзя найти")
    public void givenPersonDatabaseWithPerson_whenDeletePerson_thenPersonNotFound() {
        // Given
        PersonDatabase database = new Task3();
        Person person = new Person(1, "Charlie", "321 Elm St", "555-4321");
        database.add(person);

        // When
        database.delete(1);
        List<Person> result = database.findByPhone("555-4321");

        // Then
        assertEquals(List.of(), result);
    }

    @Test
    @DisplayName("Нельзя найти человека по номеру телефона, пока он не добавлен")
    public void givenEmptyPersonDatabase_whenFindPersonByPhone_thenPersonNotFound() {
        // Given
        PersonDatabase database = new Task3();

        // When
        List<Person> result = database.findByPhone("555-1234");

        // Then
        assertEquals(List.of(), result);
    }
}
