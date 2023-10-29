package edu.hw3.Task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class StockMarketImpl implements StockMarket {
    private final Queue<Stock> stockPriorityQueue =
        new PriorityQueue<>((s1, s2) -> Double.compare(s2.getPrice(), s1.getPrice()));

    @Override
    public void add(Stock stock) {
        stockPriorityQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockPriorityQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockPriorityQueue.peek();
    }
}
