package co.minesweepers.mystockmyway.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.minesweepers.mystockmyway.Constants;
import co.minesweepers.mystockmyway.presenter.IBasePresenter;
import co.minesweepers.mystockmyway.presenter.IStockDetailsPresenter;
import co.minesweepers.mystockmyway.presenter.StockDetailsPresenter;
import co.minesweepers.mystockmyway.view.IStockDetailView;
import co.minesweepers.mystockmyway.view.StockDetailView;

/**
 * Created by Horsie on 10/3/15.
 *
 */
public class StockDetailFragment extends Fragment {
    private IStockDetailsPresenter mPresenter = null;

    public static StockDetailFragment newInstance(String stockSymbol) {
        StockDetailFragment fragment = new StockDetailFragment();
        Bundle args = new Bundle();
        args.putString(Constants.STOCK_SYMBOL, stockSymbol);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        final IStockDetailView view = new StockDetailView();
        String stockSymbol = getArguments().getString(Constants.STOCK_SYMBOL);
        mPresenter = new StockDetailsPresenter(stockSymbol);
        mPresenter.bind(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return ((IBasePresenter) mPresenter).onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((IBasePresenter) mPresenter).onResume();
    }
}
