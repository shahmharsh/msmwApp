package co.minesweepers.mystockmyway.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import co.minesweepers.mystockmyway.R;
import co.minesweepers.mystockmyway.model.Stock;

/**
 * Created by Horsie on 10/4/15.
 *
 */
public class StockDetailView implements IBaseView, IStockDetailView, OnChartValueSelectedListener {
    private LineChart mChart;
    private TextView mTextViewX;
    private TextView mTextViewY;
    private TextView mTextViewStockSymbol;
    private TextView mTextViewHi;
    private TextView mTextViewLow;
    private TextView mTextViewClose;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stock_details_fragment, container, false);
        mTextViewStockSymbol = (TextView) rootView.findViewById(R.id.stock_name);
        mTextViewX = (TextView) rootView.findViewById(R.id.x);
        mTextViewY = (TextView) rootView.findViewById(R.id.y);
        mTextViewHi = (TextView) rootView.findViewById(R.id.text_view_high);
        mTextViewLow = (TextView) rootView.findViewById(R.id.text_view_low);
        mTextViewClose = (TextView) rootView.findViewById(R.id.text_view_close);
        mChart = (LineChart) rootView.findViewById(R.id.chart1);
        init();
        return rootView;
    }

    private void init() {
        mChart.setNoDataTextDescription("You need to provide data for the chart.");
        mChart.setOnChartValueSelectedListener(this);
    }

    private void setupChart(LineChart chart, LineData data, int color) {
        chart.setDescription("");
        chart.setDrawGridBackground(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setBackgroundColor(color);

        // set custom chart offsets (automatic offset calculation is hereby disabled)
        chart.setViewPortOffsets(10, 0, 10, 0);

        // add data
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setEnabled(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);

        chart.getXAxis().setEnabled(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

        // animate calls invalidate()...
        chart.animateX(2000);
    }

    private LineData getData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add("test" + i);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals.add(new Entry(val, i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        set1.setLineWidth(1.5f);
        set1.setCircleSize(2f);
        set1.setColor(Color.DKGRAY);
        set1.setCircleColor(Color.DKGRAY);
        set1.setHighLightColor(Color.DKGRAY);
        set1.setDrawValues(false);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);
        return data;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        String xVal = String.valueOf(e.getXIndex());
        String yVal = String.valueOf(e.getVal());
        mTextViewY.setText(yVal);
        mTextViewX.setText(xVal);
    }

    @Override
    public void onNothingSelected() {
        mTextViewY.setText("");
        mTextViewX.setText("");
    }

    @Override
    public void setStock(Stock stock) {
        mTextViewStockSymbol.setText(stock.getSymbol());
        mTextViewHi.setText(String.valueOf(stock.getHigh()));
        mTextViewLow.setText(String.valueOf(stock.getLow()));
        mTextViewClose.setText(String.valueOf(stock.getClose()));
        LineData data = getData(36, 100);
        setupChart(mChart, data, Color.rgb(89, 199, 250));
    }
}
