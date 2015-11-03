package co.minesweepers.mystockmyway.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IBasePresenter {
    void onResume();
    View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
