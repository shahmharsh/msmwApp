package co.minesweepers.mystockmyway.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.minesweepers.mystockmyway.Constants;
import co.minesweepers.mystockmyway.activity.StockDetailActivity;
import co.minesweepers.mystockmyway.manager.IStockManager;
import co.minesweepers.mystockmyway.manager.StockManager;
import co.minesweepers.mystockmyway.view.IBaseView;
import co.minesweepers.mystockmyway.view.IStockListView;

public class StockListPresenter extends BroadcastReceiver implements IBasePresenter, IStockListPresenter {
    private IStockListView mView;
    private Context mContext;
    private IStockManager mStockManager;

    public StockListPresenter(Context context) {
        mContext = context;
        mStockManager = StockManager.getInstance(mContext);
    }

    @Override
    public void onResume() {
	    LocalBroadcastManager.getInstance(mContext).registerReceiver(this, new IntentFilter(Constants.INTENT_FILTER_ACTION_STOCKS_UPDATED));
    }

	@Override
	public void onPause() {
		LocalBroadcastManager.getInstance(mContext).unregisterReceiver(this);
	}

	@Override
    public void bind(final IStockListView view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ((IBaseView) mView).createView(inflater, container, savedInstanceState);
        mView.setStocks(mStockManager.getStockSymbols());
        return view;
    }

    @Override
    public void onItemClicked(String stockSymbol) {
        Intent stockDetailsActivityIntent = new Intent(mContext, StockDetailActivity.class);
        stockDetailsActivityIntent.putExtra(Constants.STOCK_SYMBOL, stockSymbol);
        mContext.startActivity(stockDetailsActivityIntent);
    }

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Constants.INTENT_FILTER_ACTION_STOCKS_UPDATED.equals(intent.getAction())) {
			mView.setStocks(mStockManager.getStockSymbols());
		}
	}
}
