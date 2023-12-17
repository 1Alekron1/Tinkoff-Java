package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {

    @Test
    @DisplayName("При создании динамического класса, toString должен возвращать 'Hello, ByteBuddy!'")
    void whenCreatingDynamicClass_thenToStringReturnsHelloByteBuddy() throws Exception {
        // Создаем новый класс с помощью ByteBuddy
        Class<?> dynamicClass = createDynamicClass();

        // Создаем экземпляр класса
        Object instance = dynamicClass.newInstance();

        // Вызываем метод toString и проверяем результат
        Method toStringMethod = dynamicClass.getMethod("toString");
        Object result = toStringMethod.invoke(instance);

        assertEquals("Hello, ByteBuddy!", result);
    }

    private Class<?> createDynamicClass() throws Exception {
        return new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
    }
}
