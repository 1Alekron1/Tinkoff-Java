package edu.hw10;

import edu.hw10.Task1.Example;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TestTask1 {
    private static RandomObjectGenerator rog;

    @BeforeAll
    static void setUp() {
        rog = new RandomObjectGenerator();
    }

    @Test
    @DisplayName("Для класса с аннотацией @NotNull, при генерации объекта, все поля должны быть не пустыми")
    void givenClassWithNotNullAnnotation_whenGeneratingObject_thenAllFieldsNonNull() throws Exception {
        Example example = rog.nextObject(Example.class);
        assertNotNull(example.getStringValue());
    }

    @Test
    @DisplayName("Для класса с аннотациями @Min и @Max, при генерации объекта, значение поля должно находиться в указанном диапазоне")
    void givenClassWithMinMaxAnnotations_whenGeneratingObject_thenFieldValueWithinRange() throws Exception {
        Example myRecord = rog.nextObject(Example.class);
        int intValue = myRecord.getIntValue();

        assertTrue(intValue >= 1 && intValue <= 10);
    }

    @Test
    @DisplayName("Для класса с фабричным методом, при генерации объекта, должен использоваться этот метод")
    void givenClassWithFactoryMethod_whenGeneratingObject_thenUseFactoryMethod() throws Exception {
        Example example = rog.nextObject(Example.class, "create");
        assertEquals(42, example.getBasicIntValue());
    }

    @Test
    @DisplayName("Для record класса, при генерации объекта, все поля должны быть не пустыми")
    void givenRecordClass_whenGeneratingObject_thenAllFieldsNonNull() throws Exception {
        Example myRecord = rog.nextObject(Example.class);
        assertNotNull(myRecord.getIntValue());
    }
}
