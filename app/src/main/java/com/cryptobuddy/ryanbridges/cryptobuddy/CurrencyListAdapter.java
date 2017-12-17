package com.cryptobuddy.ryanbridges.cryptobuddy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ryan on 12/9/2017.
 */

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.ViewHolder> {
    private List<CurrencyListItem> currencyList;
    private CurrencyListAdapter.ViewHolder viewHolder;
    private static CustomItemClickListener listener;
    private String negativePercentStringResource;
    private String positivePercentStringResource;
    private String priceStringResource;
    private int positiveGreenColor;
    private int negativeRedColor;

    public CurrencyListAdapter(List<CurrencyListItem> currencyList, String negativePercentStringResource, String positivePercentStringResource, String priceStringResource, int positiveGreenColor, int negativeRedColor, CustomItemClickListener listener) {
        this.currencyList = currencyList;
        this.listener = listener;
        this.negativePercentStringResource = negativePercentStringResource;
        this.positivePercentStringResource = positivePercentStringResource;
        this.priceStringResource = priceStringResource;
        this.positiveGreenColor = positiveGreenColor;
        this.negativeRedColor = negativeRedColor;
    }

    @Override
    public void onBindViewHolder(CurrencyListAdapter.ViewHolder holder, int position) {
        CurrencyListItem item = currencyList.get(position);
        holder.symbolName.setText(item.symbol);
        String changeStr;
        if (item.change24hr < 0) {
            holder.changeText.setText(String.format(negativePercentStringResource, item.changePCT24hr, item.change24hr));
            holder.changeText.setTextColor(negativeRedColor);
        } else {
            holder.changeText.setText(String.format(positivePercentStringResource, item.changePCT24hr, item.change24hr));
            holder.changeText.setTextColor(positiveGreenColor);
        }

        holder.currPriceText.setText(String.format(priceStringResource, item.currPrice));
    }

    @Override
    public CurrencyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_list_item_row, parent, false);
        viewHolder = new CurrencyListAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView symbolName;
        public TextView changeText;
        public TextView currPriceText;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            itemLayoutView.setOnClickListener(this);
            symbolName = (TextView) itemLayoutView.findViewById(R.id.symbolName);
            changeText = (TextView) itemLayoutView.findViewById(R.id.changeText);
            currPriceText = (TextView) itemLayoutView.findViewById(R.id.currPriceText);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition(), v);
        }
    }

    public int getItemCount() {
        return currencyList.size();
    }

}
