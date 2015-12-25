package co.minesweepers.mystockmyway.manager;

import android.support.annotation.NonNull;

import java.util.Map;

import co.minesweepers.mystockmyway.model.Stock;

/**
 * Used for CRUD operations for stock data
 */
public interface IDBOperationsAPI {
	String DB_NAME = "STOCKS_DATA";
	int DB_VERSION = 1;

	String STOCKS_TABLE = "stocks";

	Map<String, Stock> getAllStocks();
	void putStocks(@NonNull Map<String, Stock> stocks);
}
