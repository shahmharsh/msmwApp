package co.minesweepers.mystockmyway.manager;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import co.minesweepers.mystockmyway.NetworkRequestAPI;
import co.minesweepers.mystockmyway.model.Stock;
import co.minesweepers.mystockmyway.StocksResponseParser;

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
        getStocks(null);
        Stock.Builder builder = new Stock.Builder();
        Stock stock = builder.setSymbol("Minesweepers")
                .setHigh(5f)
                .setLow(5f)
                .setClose(5f)
                .build();
        mStocks.put(stock.getSymbol(), stock);

        stock = builder.setSymbol("Google")
                .setHigh(5f)
                .setLow(5f)
                .setClose(5f)
                .build();
        mStocks.put(stock.getSymbol(), stock);

        stock = builder.setSymbol("Facebook")
                .setHigh(5f)
                .setLow(5f)
                .setClose(5f)
                .build();
        mStocks.put(stock.getSymbol(), stock);

        stock = builder.setSymbol("Apple")
                .setHigh(5f)
                .setLow(5f)
                .setClose(5f)
                .build();
        mStocks.put(stock.getSymbol(), stock);

        stock = builder.setSymbol("Yahoo")
                .setHigh(5f)
                .setLow(5f)
                .setClose(5f)
                .build();
        mStocks.put(stock.getSymbol(), stock);
    }

    public static synchronized IStockManager getInstance() {
        if (mInstance == null) {
            mInstance = new StockManager();
        }
        return mInstance;
    }

    @Override
    public Map<String, Stock> getStocksSync() {
        return mStocks;
    }

    @Override
    public Set<String> getStockSymbols() {
        return mStocks.keySet();
    }

    @Override
    public void getStocks(final StocksCallback callback) {
        Callback okHttpCallback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (callback != null) {
                    callback.onFailure();
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mStocks = StocksResponseParser.getStocks(response.body().string());
                if (callback != null) {
                    callback.onSuccess(mStocks);
                }
            }
        };

        //NetworkRequestAPI.getInstance().get(null, okHttpCallback);
    }

    @Override
    public Stock getStock(String stockSymbol) {
        return mStocks.get(stockSymbol);
    }
}
