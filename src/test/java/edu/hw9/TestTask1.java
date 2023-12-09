package edu.hw9;

import edu.hw9.Task1.MetricStats;
import edu.hw9.Task1.StatsCollector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {

    @Test
    @DisplayName("При добавлении данных в StatsCollector, считает статистику для одной метрики")
    void givenStatsCollector_whenPushDataToCollector_thenCalculateStatsForSingleMetric()
        throws InterruptedException, ExecutionException {
        StatsCollector collector = new StatsCollector(1);
        double[] values = {1.0, 2.0, 3.0};
        collector.push("metric1", values);
        List<MetricStats> stats = collector.stats();
        assertEquals(1, stats.size());
        MetricStats metricStats = stats.get(0);
        assertEquals("metric1", metricStats.getMetricName());
        assertEquals(6.0, metricStats.getSum());
        assertEquals(2.0, metricStats.getAverage());
        assertEquals(1.0, metricStats.getMin());
        assertEquals(3.0, metricStats.getMax());
    }

    @Test
    @DisplayName("При добавлении данных в StatsCollector, считает статистику для нескольких метрик")
    void givenStatsCollector_whenPushDataToCollector_thenCalculateStatsForMultipleMetrics()
        throws InterruptedException, ExecutionException {
        StatsCollector collector = new StatsCollector(2);
        double[] valuesMetric1 = {1.0, 2.0, 3.0};
        double[] valuesMetric2 = {4.0, 5.0, 6.0};
        collector.push("metric1", valuesMetric1);
        collector.push("metric2", valuesMetric2);
        List<MetricStats> stats = collector.stats();
        assertEquals(2, stats.size());
        MetricStats metricStats1 = stats.get(0);
        assertEquals("metric1", metricStats1.getMetricName());
        assertEquals(6.0, metricStats1.getSum());
        assertEquals(2.0, metricStats1.getAverage());
        assertEquals(1.0, metricStats1.getMin());
        assertEquals(3.0, metricStats1.getMax());
        MetricStats metricStats2 = stats.get(1);
        assertEquals("metric2", metricStats2.getMetricName());
        assertEquals(15.0, metricStats2.getSum());
        assertEquals(5.0, metricStats2.getAverage());
        assertEquals(4.0, metricStats2.getMin());
        assertEquals(6.0, metricStats2.getMax());
    }

    @Test
    @DisplayName("При использовании множества потоков для передачи данных, правильно считает статистику")
    void givenMultipleThreads_whenPushDataToCollector_thenCalculateStatsCorrectly()
        throws InterruptedException, ExecutionException {
        StatsCollector collector = new StatsCollector(6);

        double[] valuesMetric1 = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] valuesMetric2 = {0.5, 1.5, 2.5, 3.5, 4.5};

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                collector.push("metric1", valuesMetric1);
                collector.push("metric2", valuesMetric2);
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        List<MetricStats> stats = collector.stats();

        // Проверка корректности статистики
        for (MetricStats metric : stats) {
            if (metric.getMetricName().equals("metric1")) {
                assertEquals(75.0, metric.getSum(), 0.01);
                assertEquals(3.0, metric.getAverage(), 0.01);
                assertEquals(1.0, metric.getMin(), 0.01);
                assertEquals(5.0, metric.getMax(), 0.01);
            } else if (metric.getMetricName().equals("metric2")) {
                assertEquals(62.5, metric.getSum(), 0.01);
                assertEquals(2.5, metric.getAverage(), 0.01);
                assertEquals(0.5, metric.getMin(), 0.01);
                assertEquals(4.5, metric.getMax(), 0.01);
            }
        }
    }
}
