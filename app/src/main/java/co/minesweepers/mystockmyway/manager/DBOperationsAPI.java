package co.minesweepers.mystockmyway.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

import co.minesweepers.mystockmyway.model.Stock;

/**
 *
 */
public class DBOperationsAPI extends SQLiteOpenHelper implements IDBOperationsAPI {

	private static final String TYPE_TEXT = " text";
	private static final String TYPE_REAL = " real";
	private static final String NOT_NULL = " not null";
	private static final String COMMA = ",";
	private static final String DB_CREATE_STOCKS_TABLE = "CREATE TABLE " + STOCKS_TABLE + "("
			                                        + Stock.SYMBOL + TYPE_TEXT + NOT_NULL + COMMA
													+ Stock.HIGH +  TYPE_REAL + NOT_NULL + COMMA
													+ Stock.LOW + TYPE_REAL + NOT_NULL + COMMA
													+ Stock.CLOSE + TYPE_REAL + NOT_NULL
			                                        + ")";
	private static final String[] mAllColumns = {Stock.SYMBOL, Stock.HIGH, Stock.LOW, Stock.CLOSE};

	private static IDBOperationsAPI mInstance;
	private SQLiteDatabase mDB;

	private DBOperationsAPI(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		mDB = getWritableDatabase();
	}

	public static synchronized IDBOperationsAPI getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DBOperationsAPI(context);
		}

		return mInstance;
	}

	@Override
	public Map<String, Stock> getAllStocks() {
		Map<String, Stock> allStocks = new HashMap<>();
		Cursor cursor = mDB.query(STOCKS_TABLE, mAllColumns, null, null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			Stock stock;
			while (!cursor.isAfterLast()) {
				String symbol = cursor.getString(cursor.getColumnIndex(mAllColumns[0]));
				Double high = cursor.getDouble(cursor.getColumnIndex(mAllColumns[1]));
				Double low = cursor.getDouble(cursor.getColumnIndex(mAllColumns[2]));
				Double close = cursor.getDouble(cursor.getColumnIndex(mAllColumns[3]));
				stock = new Stock.Builder()
						        .setSymbol(symbol)
						        .setHigh(high)
						        .setLow(low)
						        .setClose(close)
						        .build();
				allStocks.put(symbol, stock);
				cursor.moveToNext();
			}
			cursor.close();
		}
		return allStocks;
	}

	@Override
	public synchronized void putStocks(Map<String, Stock> stocks) {
		ContentValues values;
		for (Stock stock : stocks.values()) {
			values = new ContentValues();
			values.put(Stock.SYMBOL, stock.getSymbol());
			values.put(Stock.HIGH, stock.getHigh());
			values.put(Stock.LOW, stock.getLow());
			values.put(Stock.CLOSE, stock.getClose());
			mDB.insert(STOCKS_TABLE, null, values);
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE_STOCKS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
