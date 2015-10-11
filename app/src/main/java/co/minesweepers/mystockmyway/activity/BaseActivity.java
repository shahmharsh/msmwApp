package co.minesweepers.mystockmyway.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import co.minesweepers.mystockmyway.R;

/**
 * Created by Horsie on 10/3/15.
 *
 */
public abstract class BaseActivity extends FragmentActivity {
    protected static final int CONTAINER_ID = R.id.fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}
