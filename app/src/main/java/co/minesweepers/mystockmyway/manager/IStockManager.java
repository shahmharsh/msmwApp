package co.minesweepers.mystockmyway.manager;

import java.util.Map;
import java.util.Set;

import co.minesweepers.mystockmyway.model.Stock;

/**
 * Created by Horsie on 10/3/15.
 *
 */
public interface IStockManager {
    Map<String, Stock> getStocksSync();
    Set<String> getStockSymbols();
    void getStocks(StocksCallback callback);
    Stock getStock(String stockSymbol);
}
