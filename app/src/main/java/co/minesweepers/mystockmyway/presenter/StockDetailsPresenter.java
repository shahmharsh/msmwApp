package co.minesweepers.mystockmyway.presenter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.minesweepers.mystockmyway.manager.StockManager;
import co.minesweepers.mystockmyway.model.Stock;
import co.minesweepers.mystockmyway.view.IBaseView;
import co.minesweepers.mystockmyway.view.IStockDetailView;

/**
 * Created by Horsie on 10/4/15.
 *
 */
public class StockDetailsPresenter implements IBasePresenter, IStockDetailsPresenter {
    private IStockDetailView mView;
    private Stock mStock;

    public StockDetailsPresenter(Context context, String stockSymbol) {
        mStock = StockManager.getInstance(context).getStockSync(stockSymbol);
    }

    @Override
    public void onResume() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ((IBaseView) mView).createView(inflater, container, savedInstanceState);
        mView.setStock(mStock);
        return view;
    }

    @Override
    public void bind(final IStockDetailView view) {
        mView = view;
    }
}
