package co.minesweepers.mystockmyway.model;

public class Stock {
    public static final String SYMBOL = "SYMBOL";
    public static final String HIGH = "HIGH";
    public static final String LOW = "LOW";
    public static final String CLOSE = "CLOSE";

    private final String mSymbol;
    private final Double mHigh;
    private Double mLow;
    private Double mClose;

    private Stock(String symbol, Double high, Double low, Double close) {
        mSymbol = symbol;
        mHigh = high;
        mLow = low;
        mClose = close;
    }

    public Double getHigh() {
        return mHigh;
    }

    public Double getLow() {
        return mLow;
    }

    public Double getClose() {
        return mClose;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public static class Builder {
        private String mSymbol;
        private Double mHigh;
        private Double mLow;
        private Double mClose;

        public Builder setSymbol(String symbol) {
            this.mSymbol = symbol;
            return this;
        }

        public Builder setHigh(Double high) {
            this.mHigh = high;
            return this;
        }

        public Builder setLow(Double low) {
            this.mLow = low;
            return this;
        }

        public Builder setClose(Double close) {
            this.mClose = close;
            return this;
        }

        public Stock build() {
            return new Stock(mSymbol, mHigh, mLow, mClose);
        }
    }
}
