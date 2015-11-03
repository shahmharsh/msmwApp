package co.minesweepers.mystockmyway.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.minesweepers.mystockmyway.presenter.IBasePresenter;
import co.minesweepers.mystockmyway.presenter.IStockListPresenter;
import co.minesweepers.mystockmyway.presenter.StockListPresenter;
import co.minesweepers.mystockmyway.view.IStockListView;
import co.minesweepers.mystockmyway.view.StockListView;

public class StockListFragment extends Fragment {
    private IStockListPresenter mPresenter = null;

    public static StockListFragment newInstance() {
        return new StockListFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        final IStockListView view = new StockListView(getActivity());
        mPresenter = new StockListPresenter(getActivity());
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
