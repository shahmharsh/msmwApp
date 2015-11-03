package co.minesweepers.mystockmyway.view;

import java.util.Set;

import co.minesweepers.mystockmyway.presenter.IStockListPresenter;

public interface IStockListView {
    void setStocks(Set<String> stocks);
    void setPresenter(IStockListPresenter presenter);
}
