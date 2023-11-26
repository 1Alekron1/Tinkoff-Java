package edu.hw7;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task4 {

    private final static Logger LOGGER = LogManager.getLogger();
    public static final double CONSTATN_FOR_PI = 4.0;

    private Task4() {
    }

    static double calculatePiSingleThread(int simulations) {
        int totalCount = 0;
        int circleCount = 0;

        for (int i = 0; i < simulations; i++) {
            double x = Math.random();
            double y = Math.random();
            double distance = Math.sqrt(x * x + y * y);

            if (distance <= 1) {
                circleCount++;
            }

            totalCount++;
        }

        return CONSTATN_FOR_PI * (double) circleCount / totalCount;
    }

    static double calculatePiMultiThread(int simulations, int numThreads) {
        AtomicInteger totalCount = new AtomicInteger(0);
        AtomicInteger circleCount = new AtomicInteger(0);

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < simulations / numThreads; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();
                    double distance = Math.sqrt(x * x + y * y);

                    if (distance <= 1) {
                        circleCount.incrementAndGet();
                    }

                    totalCount.incrementAndGet();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return CONSTATN_FOR_PI * (double) circleCount.get() / totalCount.get();
    }

    private static void calculateError(int simulations) {
        double actualPi = Math.PI;
        double piSingleThread = calculatePiSingleThread(simulations);
        double piMultiThread = calculatePiMultiThread(simulations, Runtime.getRuntime().availableProcessors());

        double errorSingleThread = Math.abs(piSingleThread - actualPi);
        double errorMultiThread = Math.abs(piMultiThread - actualPi);
        LOGGER.info("\nПогрешность для " + simulations + " симуляций:");
        LOGGER.info("Однопоточная версия: " + errorSingleThread);
        LOGGER.info("Многопоточная версия: " + errorMultiThread);
    }
}
