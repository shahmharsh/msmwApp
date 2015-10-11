package co.minesweepers.mystockmyway.manager;

import java.util.Map;

import co.minesweepers.mystockmyway.model.Stock;

/**
 * Created by Horsie on 10/3/15.
 *
 */
public interface StocksCallback {
    void onSuccess(Map<String, Stock> stocks);
    void onFailure();
}
