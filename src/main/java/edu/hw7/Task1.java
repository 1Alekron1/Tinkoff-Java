package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public static final int MAX_COUNT = 10000;

    Task1() {
    }

    private AtomicInteger counter = new AtomicInteger(0);

    public static boolean isAtomicInteger(AtomicInteger counter) {
        return counter instanceof AtomicInteger;
    }

    public AtomicInteger getCounter(int numberOfThreads) {
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new CounterIncrementer());
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }

        return counter;
    }

    class CounterIncrementer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < MAX_COUNT; i++) {
                counter.incrementAndGet();
            }
        }
    }
}
