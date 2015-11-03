package co.minesweepers.mystockmyway.manager;

import java.util.Map;

import co.minesweepers.mystockmyway.model.Stock;

public interface StocksCallback {
    void onSuccess(Map<String, Stock> stocks);
    void onFailure(int errorCode);
}
