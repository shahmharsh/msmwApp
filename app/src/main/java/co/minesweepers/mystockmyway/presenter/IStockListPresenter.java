package co.minesweepers.mystockmyway.presenter;

import co.minesweepers.mystockmyway.view.IStockListView;

public interface IStockListPresenter {
    void bind(IStockListView view);
    void onItemClicked(String stockSymbol);
}
