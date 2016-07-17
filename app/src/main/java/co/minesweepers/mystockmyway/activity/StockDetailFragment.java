package co.minesweepers.mystockmyway.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.minesweepers.mystockmyway.Constants;
import co.minesweepers.mystockmyway.model.Stock;
import co.minesweepers.mystockmyway.presenter.IBasePresenter;
import co.minesweepers.mystockmyway.presenter.IStockDetailsPresenter;
import co.minesweepers.mystockmyway.presenter.StockDetailsPresenter;
import co.minesweepers.mystockmyway.view.IStockDetailView;
import co.minesweepers.mystockmyway.view.StockDetailView;

public class StockDetailFragment extends Fragment {
    private IStockDetailsPresenter mPresenter = null;

    public static StockDetailFragment newInstance(String stockSymbol) {
        StockDetailFragment fragment = new StockDetailFragment();
        Bundle args = new Bundle();
        args.putString(Stock.SYMBOL, stockSymbol);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        final IStockDetailView view = new StockDetailView();
        String stockSymbol = getArguments().getString(Stock.SYMBOL);
        mPresenter = new StockDetailsPresenter(getActivity().getApplicationContext(), stockSymbol);
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

	@Override
	public void onPause() {
		super.onPause();
		((IBasePresenter) mPresenter).onPause();
	}
}
