package co.minesweepers.mystockmyway.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Horsie on 10/4/15.
 *
 */
public interface IBaseView {
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
