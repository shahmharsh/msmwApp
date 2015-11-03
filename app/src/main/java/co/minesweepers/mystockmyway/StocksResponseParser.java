package co.minesweepers.mystockmyway;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import co.minesweepers.mystockmyway.model.Stock;

public class StocksResponseParser {

    // TODO: change implementation to assume its a json array (once array structure is defined by backend)
    public static Map<String, Stock> getStocks(String json) throws JSONException {
        Map<String, Stock> stocks = new HashMap<>();
        JSONObject jsonObject = new JSONObject(json);
        String symbol = jsonObject.getString(Stock.SYMBOL);
        Double high = jsonObject.getDouble(Stock.HIGH);
        Double low = jsonObject.getDouble(Stock.LOW);
        Double close = jsonObject.getDouble(Stock.CLOSE);
        Stock stock = new Stock.Builder()
                              .setSymbol(symbol)
                              .setHigh(high)
                              .setLow(low)
                              .setClose(close)
                              .build();
        stocks.put(symbol, stock);
        return stocks;
    }
}
