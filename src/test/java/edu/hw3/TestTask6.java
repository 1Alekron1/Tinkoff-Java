package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarket;
import edu.hw3.Task6.StockMarketImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestTask6 {
    @Test
    @DisplayName("Тест: получение самой дорогой акции в пустом рынке")
    public void testMostValuableStockEmptyMarket() {
        StockMarket market = new StockMarketImpl();
        assertNull(market.mostValuableStock());
    }

    @Test
    @DisplayName("Тест: получение самой дорогой акции")
    public void testMostValuableStock() {
        StockMarket market = new StockMarketImpl();

        Stock stock1 = new Stock(12.0);
        Stock stock2 = new Stock(3400.0);
        Stock stock3 = new Stock(300.0);

        market.add(stock1);
        market.add(stock2);
        market.add(stock3);

        assertEquals(stock2, market.mostValuableStock());

        market.remove(stock2);
        assertEquals(stock3, market.mostValuableStock());

        market.remove(stock3);
        assertEquals(stock1, market.mostValuableStock());

        market.remove(stock1);
        assertNull(market.mostValuableStock());
    }
}
