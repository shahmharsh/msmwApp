package co.minesweepers.mystockmyway.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import co.minesweepers.mystockmyway.Constants;
import co.minesweepers.mystockmyway.R;
import co.minesweepers.mystockmyway.manager.StockManager;
import co.minesweepers.mystockmyway.model.Stock;

public class StockListActivity extends Activity implements StocksAdapter.Callback {
    private static final String TAG = "StockListActivity";
    private RecyclerView mRecyclerView;
    private StocksAdapter mStocksAdapter;
    private BroadcastReceiver mStocksBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mStocksBroadcastReceiver, new IntentFilter(Constants.INTENT_FILTER_ACTION_STOCKS_UPDATED));
        mStocksAdapter.setStocks(StockManager.getInstance(this).getStockSymbolsAsList());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mStocksBroadcastReceiver);
    }

    private void setup() {
        mStocksAdapter = new StocksAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mStocksAdapter);

        mStocksBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Constants.INTENT_FILTER_ACTION_STOCKS_UPDATED.equals(intent.getAction())) {
                    mStocksAdapter.setStocks(StockManager.getInstance(StockListActivity.this).getStockSymbolsAsList());
                }
            }
        };
    }

    @Override
    public void onStocksItemClick(String symbol) {
        Log.d(TAG, "Stock clicked: " + symbol);
    }
}
