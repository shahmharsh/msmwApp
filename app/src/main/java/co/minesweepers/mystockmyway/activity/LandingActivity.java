package co.minesweepers.mystockmyway.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Horsie on 10/3/15.
 *
 */
public class LandingActivity extends BaseActivity {

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
