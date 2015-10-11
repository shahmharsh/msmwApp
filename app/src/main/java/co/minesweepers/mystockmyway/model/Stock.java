package co.minesweepers.mystockmyway.model;

/**
 * Created by Horsie on 10/3/15.
 *
 */
public class Stock {
    private final String mSymbol;
    private final float mHigh;
    private float mLow;
    private float mClose;

    private Stock(String symbol, float high, float low, float close) {
        mSymbol = symbol;
        mHigh = high;
        mLow = low;
        mClose = close;
    }

    public float getHigh() {
        return mHigh;
    }

    public float getLow() {
        return mLow;
    }

    public float getClose() {
        return mClose;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public static class Builder {
        private String mSymbol;
        private float mHigh;
        private float mLow;
        private float mClose;

        public Builder setSymbol(String symbol) {
            this.mSymbol = symbol;
            return this;
        }

        public Builder setHigh(float high) {
            this.mHigh = high;
            return this;
        }

        public Builder setLow(float low) {
            this.mLow = low;
            return this;
        }

        public Builder setClose(float close) {
            this.mClose = close;
            return this;
        }

        public Stock build() {
            return new Stock(mSymbol, mHigh, mLow, mClose);
        }
    }
}
