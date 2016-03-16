package co.minesweepers.mystockmyway;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import co.minesweepers.mystockmyway.model.Stock;
import co.minesweepers.mystockmyway.model.StockData;

public class StocksResponseParser {

	private static final String TIMESTAMP_DATE_PATTERN = "EEE MMM dd HH:mm:ss Z yyyy";

    public static ArrayList<StockData> getHighLowCloseFromArray(String jsonArrayString) throws JSONException, ParseException {
        ArrayList<StockData> stockDataArray = new ArrayList<>();
	    JSONArray jsonArray = new JSONArray(jsonArrayString);
	    for(int i=0; i<jsonArray.length(); i++) {
		    JSONObject jsonStockData = jsonArray.getJSONObject(i);
		    String timestamp = jsonStockData.getString(Stock.TIMESTAMP);
		    Date date = new SimpleDateFormat(TIMESTAMP_DATE_PATTERN, Locale.US).parse(timestamp);
		    Double high = jsonStockData.getDouble(Stock.HIGH);
		    Double low = jsonStockData.getDouble(Stock.LOW);
		    Double close = jsonStockData.getDouble(Stock.CLOSE);
		    StockData stockData = new StockData.Builder()
				                  .setDate(date)
				                  .setHigh(high)
				                  .setLow(low)
				                  .setClose(close)
				                  .build();
		    stockDataArray.add(stockData);
	    }

        return stockDataArray;
    }
}
