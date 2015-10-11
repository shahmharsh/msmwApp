package co.minesweepers.mystockmyway.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.minesweepers.mystockmyway.Constants;
import co.minesweepers.mystockmyway.activity.StockDetailActivity;
import co.minesweepers.mystockmyway.manager.IStockManager;
import co.minesweepers.mystockmyway.manager.StockManager;
import co.minesweepers.mystockmyway.view.IBaseView;
import co.minesweepers.mystockmyway.view.IStockListView;

/**
 * Created by Horsie on 10/4/15.
 *
 */
public class StockListPresenter implements IBasePresenter, IStockListPresenter {
    private IStockListView mView;
    private Context mContext;
    private IStockManager mStockManager;

    public StockListPresenter(Context context) {
        mContext = context;
        mStockManager = StockManager.getInstance();
    }

    @Override
    public void onResume() {

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
}
