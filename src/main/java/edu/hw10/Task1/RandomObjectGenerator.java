package edu.hw10.Task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.UUID;

public class RandomObjectGenerator {
    public <T> T nextObject(Class<T> clazz, String factoryMethod) throws Exception {
        if (clazz.isRecord()) {
            return generateRecord(clazz);
        } else {
            return generatePojo(clazz, factoryMethod);
        }
    }

    public <T> T nextObject(Class<T> clazz) throws Exception {
        return nextObject(clazz, null);
    }

    private <T> T generateRecord(Class<T> clazz)
        throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T instance = clazz.getDeclaredConstructor().newInstance();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            setValue(instance, field);
        }

        return instance;
    }

    private <T> T generatePojo(Class<T> clazz, String factoryMethod) throws Exception {
        Constructor<?>[] constructors = clazz.getConstructors();
        Object instance;

        if (factoryMethod != null) {
            Method method = clazz.getMethod(factoryMethod);
            instance = method.invoke(null);
        } else {
            instance = constructors[0].newInstance();
        }

        for (Field field : instance.getClass().getDeclaredFields()) {
            setValue(instance, field);
        }

        return (T) instance;
    }

    private void setValue(Object instance, Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(Min.class)
            || field.isAnnotationPresent(Max.class)) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            Object value = generateRandomValue(fieldType, field.getAnnotations());
            field.set(instance, value);
        }
    }

    private Object generateRandomValue(Class<?> type, Annotation[] annotations) {
        Random random = new Random();
        Object value = null;

        if (type == int.class || type == Integer.class) {
            value = generateIntValue(annotations, random);
        } else if (type == double.class || type == Double.class) {
            value = generateDoubleValue(annotations, random);
        } else if (type == boolean.class || type == Boolean.class) {
            value = random.nextBoolean();
        } else if (type == String.class) {
            value = UUID.randomUUID().toString();
        }
        // Add support for more types as needed
        return value;
    }

    private int generateIntValue(Annotation[] annotations, Random random) {
        int minValue = Integer.MIN_VALUE;
        int maxValue = Integer.MAX_VALUE;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                minValue = Math.max(minValue, ((Min) annotation).value());
            } else if (annotation instanceof Max) {
                maxValue = Math.min(maxValue, ((Max) annotation).value());
            }
        }

        return random.nextInt((maxValue - minValue) + 1) + minValue;
    }

    private double generateDoubleValue(Annotation[] annotations, Random random) {
        double minValue = Double.MIN_VALUE;
        double maxValue = Double.MAX_VALUE;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                minValue = Math.max(minValue, ((Min) annotation).value());
            } else if (annotation instanceof Max) {
                maxValue = Math.min(maxValue, ((Max) annotation).value());
            }
        }

        return minValue + (maxValue - minValue) * random.nextDouble();
    }
}
