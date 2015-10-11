package co.minesweepers.mystockmyway.presenter;

import co.minesweepers.mystockmyway.view.IStockListView;

/**
 * Created by Horsie on 10/4/15.
 *
 */
public interface IStockListPresenter {
    void bind(IStockListView view);
    void onItemClicked(String stockSymbol);
}
