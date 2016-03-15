package co.minesweepers.mystockmyway.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class StockListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Fragment fragment = StockListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(CONTAINER_ID, fragment).commit();
        }
    }
}
