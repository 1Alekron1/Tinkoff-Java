package edu.hw10.Task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheProxy implements InvocationHandler {

    private final Object target;
    private final Map<String, Object> cache;
    private final static Logger LOGGER = LogManager.getLogger();
    private final String cacheDirectory;

    private CacheProxy(Object target, String cacheDirectory) {
        this.target = target;
        this.cache = new HashMap<>();
        this.cacheDirectory = cacheDirectory;

        // Create cache directory if not exists
        File directory = new File(cacheDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Object target, Class<T> interfaceClass, String cacheDirectory) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            new CacheProxy(target, cacheDirectory)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            if (cacheAnnotation.persist()) {
                // Check cache
                String key = generateCacheKey(method, args);
                if (cache.containsKey(key)) {
                    return cache.get(key);
                }

                // If not in cache, check disk
                Object result = loadFromDisk(key);
                if (result != null) {
                    cache.put(key, result);
                    return result;
                }

                // Invoke the method and cache the result
                result = method.invoke(target, args);
                cache.put(key, result);
                saveToDisk(key, result);

                return result;
            }
        }

        // For methods without @Cache annotation, simply invoke the method
        return method.invoke(target, args);
    }

    private String generateCacheKey(Method method, Object[] args) {
        StringBuilder keyBuilder = new StringBuilder(method.getName());
        for (Object arg : args) {
            keyBuilder.append("_").append(arg.toString());
        }
        return keyBuilder.toString();
    }

    private void saveToDisk(String key, Object value) {
        try {
            String filePath = cacheDirectory + File.separator + key;
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(value);
            }
        } catch (IOException e) {
            LOGGER.info(e.getStackTrace());
        }
    }

    private Object loadFromDisk(String key) {
        try {
            String filePath = cacheDirectory + File.separator + key;
            if (Files.exists(Paths.get(filePath))) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                    return ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.info(e.getStackTrace());
        }
        return null;
    }
}
