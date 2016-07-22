package co.minesweepers.mystockmyway.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.minesweepers.mystockmyway.R;

public class StocksAdapter extends RecyclerView.Adapter<StocksAdapter.StockViewHolder> {

    private List<String> mStocks;
    private Callback mListener;

    public StocksAdapter(Callback listener) {
        mListener = listener;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View stockView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item, parent, false);
        return new StockViewHolder(stockView, mListener);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.bindStock(mStocks.get(position));
    }

    @Override
    public int getItemCount() {
        return mStocks.size();
    }

    public void setStocks(List <String> stocks) {
        if (stocks == null) {
            throw new RuntimeException("Stocks list cannot be null");
        }
        mStocks = null;
        mStocks = stocks;
        notifyDataSetChanged();
    }

    public class StockViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Callback mListener;
        private String mStockSymbol;
        private TextView mTextViewSymbol;

        public StockViewHolder(View itemView, Callback listener) {
            super(itemView);
            mTextViewSymbol = (TextView) itemView.findViewById(R.id.stock_symbol);
            itemView.setOnClickListener(this);
            mListener = listener;
        }

        public void bindStock(String stockSymbol) {
            mStockSymbol = stockSymbol;
            mTextViewSymbol.setText(mStockSymbol);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onStocksItemClick(mStockSymbol);
            }
        }
    }

    public interface Callback {
        void onStocksItemClick(String symbol);
    }
}
