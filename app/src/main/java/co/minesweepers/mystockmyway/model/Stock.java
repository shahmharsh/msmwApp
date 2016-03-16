package co.minesweepers.mystockmyway.model;

import java.util.ArrayList;

public class Stock {
    public static final String SYMBOL = "SYMBOL";
    public static final String HIGH = "HIGH";
    public static final String LOW = "LOW";
    public static final String CLOSE = "CLOSE";
	public static final String TIMESTAMP = "TIMESTAMP";

    private final String mSymbol;
    private ArrayList<StockData> mStockData;

    public Stock(String symbol, ArrayList<StockData> stockData) {
        mSymbol = symbol;
	    mStockData = stockData;
    }

    public String getSymbol() {
        return mSymbol;
    }

	public ArrayList<StockData> getData() {
		return mStockData;
	}
}
