package co.minesweepers.mystockmyway.manager;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import co.minesweepers.mystockmyway.Constants;
import co.minesweepers.mystockmyway.NetworkRequestAPI;
import co.minesweepers.mystockmyway.StockErrors;
import co.minesweepers.mystockmyway.StocksResponseParser;
import co.minesweepers.mystockmyway.model.Stock;

/**
 * Created by Horsie on 10/3/15.
 *
 */
public class StockManager implements IStockManager {

    private static StockManager mInstance;
    private Map<String, Stock> mStocks;

    private StockManager() {
        mStocks = new HashMap<>();
        init();
    }

    private void init() {
        getStock("NIFTY", null);
    }

    public static synchronized IStockManager getInstance() {
        if (mInstance == null) {
            mInstance = new StockManager();
        }
        return mInstance;
    }

    @Override
    public Set<String> getStockSymbols() {
        return mStocks.keySet();
    }

    @Override
    public Map<String, Stock> getStocksSync() {
        return mStocks;
    }

    @Override
    public void getStocks(final StocksCallback callback) {
        Callback okHttpCallback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (callback != null) {
                    callback.onFailure(StockErrors.UNKNOWN_SERVER_ERROR);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    mStocks.putAll(StocksResponseParser.getStocks(response.body().string()));
                } catch (JSONException e) {
                    if (callback != null) {
                        callback.onFailure(StockErrors.JSON_PARSE_ERROR);
                    }
                }

                if (callback != null) {
                    callback.onSuccess(mStocks);
                }
            }
        };

        NetworkRequestAPI.getInstance().get(null, okHttpCallback);
    }

    @Override
    public Stock getStockSync(String stockSymbol) {
        return mStocks.get(stockSymbol);
    }

    @Override
    public void getStock(String stockSymbol, final StocksCallback callback) {
        Callback okHttpCallback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (callback != null) {
                    callback.onFailure(StockErrors.UNKNOWN_SERVER_ERROR);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    mStocks.putAll(StocksResponseParser.getStocks(response.body().string()));
                } catch (JSONException e) {
                    if (callback != null) {
                        callback.onFailure(StockErrors.JSON_PARSE_ERROR);
                    }
                }

                if (callback != null) {
                    callback.onSuccess(mStocks);
                }
            }
        };

        HttpUrl url = new HttpUrl.Builder()
                              .scheme(Constants.HTTP_SCHEME)
                              .host(Constants.SERVER_BASE_URL)
                              .port(Constants.SERVER_PORT)
                              .addEncodedPathSegment(Constants.GET_STOCK_PATH)
                              .addEncodedPathSegment(stockSymbol)
                              .build();

        NetworkRequestAPI.getInstance().get(url, okHttpCallback);
    }
}
