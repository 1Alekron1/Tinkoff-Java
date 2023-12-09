package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {
    public static final int MAX_ARRAY_SIZE = 1000;
    public static final int RANGE_SCALE = 10;
    private final Map<String, List<Double>> dataMap;
    private final ExecutorService executorService;

    public StatsCollector(int threadPoolSize) {
        this.dataMap = new ConcurrentHashMap<>();
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void push(String metricName, double[] values) {
        if (!dataMap.containsKey(metricName)) {
            dataMap.put(metricName, new CopyOnWriteArrayList<>());
        }
        List<Double> dataList = dataMap.get(metricName);
        for (double value : values) {
            dataList.add(value);
        }
    }

    public List<MetricStats> stats() throws InterruptedException, ExecutionException {
        List<Future<MetricStats>> futures = new ArrayList<>();

        for (Map.Entry<String, List<Double>> entry : dataMap.entrySet()) {
            String metricName = entry.getKey();
            List<Double> values = entry.getValue();

            Future<MetricStats> future = executorService.submit(() -> calculateStats(metricName, values));
            futures.add(future);
        }

        List<MetricStats> result = new ArrayList<>();
        for (Future<MetricStats> future : futures) {
            result.add(future.get());
        }

        return result;
    }

    private MetricStats calculateStats(String metricName, List<Double> values) {
        double sum = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (double value : values) {
            sum += value;
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        double average = values.isEmpty() ? 0 : sum / values.size();
        return new MetricStats(metricName, sum, average, min, max);
    }

    public void shutdown() {
        executorService.shutdown();
    }


    private static void generateData(StatsCollector collector, String metricName) {
        Random random = new Random();
        double[] values = new double[MAX_ARRAY_SIZE];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextDouble() * RANGE_SCALE;
        }

        collector.push(metricName, values);
    }
}
