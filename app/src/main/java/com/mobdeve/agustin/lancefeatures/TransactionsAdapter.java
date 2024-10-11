package com.mobdeve.agustin.lancefeatures;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    private ArrayList<Transactions> transactionsList;

    public TransactionsAdapter(ArrayList<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transactions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transactions transaction = transactionsList.get(position);
        holder.textViewItems.setText("Items: " + transaction.getItems());
        holder.textViewTotal.setText("Total: " + transaction.getTotal());
        holder.textViewDiscount.setText("" + transaction.getDiscount());
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItems;
        public TextView textViewTotal;
        public TextView textViewDiscount;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewItems = itemView.findViewById(R.id.textViewItems);
            textViewTotal = itemView.findViewById(R.id.textViewTotal);
            textViewDiscount = itemView.findViewById(R.id.textViewDiscount);
        }
    }
}
