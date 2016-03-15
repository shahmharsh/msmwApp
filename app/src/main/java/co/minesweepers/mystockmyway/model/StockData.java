package co.minesweepers.mystockmyway.model;

import java.util.Date;

public class StockData {
	private Double mHigh;
	private Double mLow;
	private Double mClose;
	private Date mDate;

	private StockData(Date date, Double high, Double low, Double close) {
		mDate = date;
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

	public Date getDate() {
		return mDate;
	}

	public static class Builder {
		private Date mDate;
		private Double mHigh;
		private Double mLow;
		private Double mClose;

		public Builder setDate(Date date) {
			this.mDate = date;
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

		public StockData build() {
			return new StockData(mDate, mHigh, mLow, mClose);
		}
	}
}
