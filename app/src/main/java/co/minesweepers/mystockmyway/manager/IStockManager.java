package co.minesweepers.mystockmyway.manager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import co.minesweepers.mystockmyway.model.Stock;

/**
 * Manages all the stocks
 */
public interface IStockManager {


	/**
	 * Returns {@code Set} of all stock symbols available
	 *
	 * @return {@code Set} of all stock symbols available
	 */
	Set<String> getStockSymbols();




    /**
     * Returns {@code List} of all stock symbols available
     *
     * @return {@code List} of all stock symbols available
     */
    List<String> getStockSymbolsAsList();




	/**
	 * Returns {@code Map} of stock symbol and {@code Stock} that are cached
	 * This is a synchronous call
	 * Note: Returns empty Map when no stocks in cache
	 *
	 * @return {@code Map} of stock symbol and {@code Stock} that are cached
	 */
    Map<String, Stock> getAllStocks();




	/**
	 * Returns all the {@code Stock} fetched from server in callback
	 * Note: Returns empty Map when no stocks available on server
	 *
	 * @param callback
	 *          {@code StocksCallback}
	 */
    void getAllStocks(@Nullable StocksCallback callback);




	/**
	 * Returns the {@code Stock} of the specified stock symbol.
	 * This is a synchronous call.
	 *
	 * @param stockSymbol
	 *          the stock symbol
	 * @return the the {@code Stock} of the specified stock symbol, or {@code null}
	 *         if no {@code Stock} for the specified stock symbol is found.
	 */
    Stock getStock(@NonNull String stockSymbol);




	/**
	 * Fetches the specified stock until the specified date from server and returns it in callback
	 *
	 * @param stockSymbol
	 *          the stock symbol
	 * @param date
	 *          Date until which the stock data is requested
	 * @param callback
	 *          {@code StocksCallback}
	 */
	void getStock(@NonNull String stockSymbol, @NonNull Date date, @Nullable StocksCallback callback);




	/**
	 * Fetches the specified stock until current system date from server and returns it in callback
	 *
	 * @param stockSymbol
	 *          the stock symbol
	 * @param callback
	 *          {@code StocksCallback}
	 */
    void getStock(@NonNull String stockSymbol, @Nullable StocksCallback callback);
}
