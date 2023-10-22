package edu.hw2;

import edu.hw2.task4.CallingInfo;
import edu.hw2.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTask4 {

    @Test
    @DisplayName("Тест: Получение информации о вызывающем методе")
    void testCallingInfo() {
        CallingInfo callingInfo = Task4.callingInfo();
        assertNotNull(callingInfo, "CallingInfo не должно быть null");
        assertEquals("edu.hw2.TestTask4", callingInfo.getClassName(), "Неверное имя класса");
        assertEquals("testCallingInfo", callingInfo.getMethodName(), "Неверное имя метода");
    }

    @Test
    @DisplayName("Тест: Получение информации о вызывающем методе из статического метода")
    void testCallingInfoFromStaticMethod() {
        CallingInfo callingInfo = AnotherClass.callingInfoFromStaticMethod();

        assertNotNull(callingInfo, "CallingInfo не должно быть null");
        assertEquals("edu.hw2.AnotherClass", callingInfo.getClassName(), "Неверное имя класса");
        assertEquals("callingInfoFromStaticMethod", callingInfo.getMethodName(), "Неверное имя метода");
    }
}

class AnotherClass {
    static CallingInfo callingInfoFromStaticMethod() {
        return Task4.callingInfo();
    }
}
