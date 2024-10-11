package com.mobdeve.agustin.lancefeatures;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private ArrayList<Coffee> coffeeItems;
    private OnItemRemovedListener onItemRemovedListener;

    public interface OnItemRemovedListener {
        void onItemRemoved(int total);
    }

    public CoffeeAdapter(ArrayList<Coffee> coffeeItems, OnItemRemovedListener listener) {
        this.coffeeItems = coffeeItems;
        this.onItemRemovedListener = listener;
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coffee, parent, false);
        return new CoffeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        Coffee item = coffeeItems.get(position);
        holder.itemCount.setText(String.valueOf(item.getCount()));
        holder.itemName.setText(item.getName());
        holder.itemPrice.setText(String.valueOf(item.getTotalPrice()));

        holder.buttonIncrement.setOnClickListener(v -> {
            item.incrementCount();
            notifyItemChanged(position);
            onItemRemovedListener.onItemRemoved(item.getPrice());
        });

        holder.buttonDecrement.setOnClickListener(v -> {
            if (item.decrementCount() == 0) {
                coffeeItems.remove(position);
                notifyItemRemoved(position);
            } else {
                notifyItemChanged(position);
            }
            onItemRemovedListener.onItemRemoved(item.getPrice());
        });

        holder.buttonRemove.setOnClickListener(v -> {
            coffeeItems.remove(position);
            notifyItemRemoved(position);
            onItemRemovedListener.onItemRemoved(item.getPrice());
        });
    }

    @Override
    public int getItemCount() {
        return coffeeItems.size();
    }

    public static class CoffeeViewHolder extends RecyclerView.ViewHolder {
        TextView itemCount, itemName, itemPrice;
        Button buttonIncrement, buttonDecrement, buttonRemove;

        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCount = itemView.findViewById(R.id.itemCount);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            buttonIncrement = itemView.findViewById(R.id.buttonIncrement);
            buttonDecrement = itemView.findViewById(R.id.buttonDecrement);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
        }
    }
}

