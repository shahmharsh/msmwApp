package co.minesweepers.mystockmyway.manager;

import java.util.Map;
import java.util.Set;

import co.minesweepers.mystockmyway.model.Stock;

/**
 * Manages all the stocks
 */
public interface IStockManager {
    Set<String> getStockSymbols();
    Map<String, Stock> getStocksSync();
    void getStocks(StocksCallback callback);
    Stock getStockSync(String stockSymbol);
    void getStock(String stockSymbol, StocksCallback callback);
}
