package co.minesweepers.mystockmyway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import co.minesweepers.mystockmyway.Constants;

public class StockDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            String stockSymbol = intent.getStringExtra(Constants.STOCK_SYMBOL);
            Fragment fragment = StockDetailFragment.newInstance(stockSymbol);
            getSupportFragmentManager().beginTransaction()
                    .add(CONTAINER_ID, fragment).commit();
        }
    }
}
