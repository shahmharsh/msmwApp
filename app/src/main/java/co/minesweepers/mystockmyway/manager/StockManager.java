package co.minesweepers.mystockmyway.manager;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import co.minesweepers.mystockmyway.Constants;
import co.minesweepers.mystockmyway.StockErrors;
import co.minesweepers.mystockmyway.StocksResponseParser;
import co.minesweepers.mystockmyway.model.Stock;

public class StockManager implements IStockManager {

    private static StockManager mInstance;
    private Map<String, Stock> mStocks;
    private Context mContext;

    private StockManager(Context context) {
        mStocks = new HashMap<>();
        mContext = context;
        init();
    }

    private void init() {
	    getCommonStocks();
	    //TODO: Get all stocks from DB
    }

    private void getCommonStocks() {
        getStock("NIFTY", null);
        getStock("BANKNIFTY", null);
        getStock("CNXIT", null);
    }

    public static synchronized IStockManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new StockManager(context);
        }
        return mInstance;
    }

    @Override
    public Set<String> getStockSymbols() {
        return mStocks.keySet();
    }

    @Override
    public Map<String, Stock> getAllStocks() {
        return mStocks;
    }

    @Override
    public void getAllStocks(final @Nullable StocksCallback callback) {
        //TODO: Server currently does not give list of stocks
    }

    @Override
    public Stock getStock(@NonNull String stockSymbol) {
        return mStocks.get(stockSymbol);
    }

	@Override
	public void getStock(@NonNull final String stockSymbol, @NonNull Date date, @Nullable final StocksCallback callback) {
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
					Stock stock = new Stock(stockSymbol, StocksResponseParser.getHighLowCloseFromArray(response.body().string()));
					mStocks.put(stockSymbol, stock);
					//TODO: Update DB
					sendStocksUpdatedLocalBroadcast();
				} catch (JSONException e) {
					if (callback != null) {
						callback.onFailure(StockErrors.JSON_PARSE_ERROR);
					}
					return;
				} catch (ParseException e) {
					if (callback != null) {
						callback.onFailure(StockErrors.DATE_PARSE_ERROR);
					}
					return;
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
							  .addEncodedPathSegment(Constants.DATE_PATH)
							  .addEncodedPathSegment(new SimpleDateFormat(Constants.DATE_TEMPLATE_FORMAT_YYYY_MM_dd, Locale.US).format(date))
				              .build();

		NetworkRequestAPI.getInstance().get(url, okHttpCallback);
	}

    @Override
    public void getStock(final @NonNull String stockSymbol, final @Nullable StocksCallback callback) {
	    getStock(stockSymbol, new GregorianCalendar(Locale.US).getTime(), callback);
    }

	private void sendStocksUpdatedLocalBroadcast() {
		Intent intent = new Intent(Constants.INTENT_FILTER_ACTION_STOCKS_UPDATED);
		LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
	}
}
