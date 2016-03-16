package co.minesweepers.mystockmyway.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.Map;

import co.minesweepers.mystockmyway.model.Stock;

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
		return null;
	}

	@Override
	public synchronized void putStocks(@NonNull Map<String, Stock> stocks) {

	}

	@Override
	public void putStock(@NonNull String stockSymbol, @NonNull Stock stock) {

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE_STOCKS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
