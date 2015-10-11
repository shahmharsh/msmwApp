package co.minesweepers.mystockmyway.view;

import java.util.Set;

import co.minesweepers.mystockmyway.presenter.IStockListPresenter;

/**
 * Created by Horsie on 10/3/15.
 *
 */
public interface IStockListView {
    void setStocks(Set<String> stocks);
    void setPresenter(IStockListPresenter presenter);
}
