package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int poolSize;
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] threads;
    private final CountDownLatch latch;

    public FixedThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new Thread[poolSize];
        this.latch = new CountDownLatch(poolSize);
    }

    @Override
    public void start() {
        for (int i = 0; i < poolSize; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    try {
                        Runnable task = taskQueue.take();
                        task.run();
                        latch.countDown();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        try {
            latch.await();
            for (Thread thread : threads) {
                thread.interrupt();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
