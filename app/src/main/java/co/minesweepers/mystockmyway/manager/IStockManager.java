package co.minesweepers.mystockmyway.manager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

import co.minesweepers.mystockmyway.model.Stock;

/**
 * Manages all the stocks
 */
public interface IStockManager {
    Set<String> getStockSymbols();
    Map<String, Stock> getAllStocksSync();
    void getAllStocksFromNetwork(@Nullable StocksCallback callback);
    Stock getStockSync(@NonNull String stockSymbol);
    void getStock(@NonNull String stockSymbol,@Nullable StocksCallback callback);
    void getAllStocksFromDB(@Nullable StocksCallback callback);
}
