package co.minesweepers.mystockmyway.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;

import co.minesweepers.mystockmyway.R;
import co.minesweepers.mystockmyway.presenter.IStockListPresenter;

public class StockListView implements IBaseView, IStockListView, AdapterView.OnItemClickListener {

    private IStockListPresenter mPresenter;
    private ArrayAdapter<String> mArrayAdapter;
    private Context mContext;

    public StockListView (Context context) {
        mContext = context;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stocks_list_fragment, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.stocks_list);
        mArrayAdapter = new ArrayAdapter<>(mContext, R.layout.stock_item);
        listView.setAdapter(mArrayAdapter);
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void setStocks(Set<String> stocks) {
        mArrayAdapter.clear();
        mArrayAdapter.addAll(stocks);
    }

    @Override
    public void setPresenter(IStockListPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedStockSymbol = mArrayAdapter.getItem(position);
        mPresenter.onItemClicked(selectedStockSymbol);
    }
}
