package com.mobdeve.agustin.lancefeatures;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {

    private ArrayList<ItemInventory> inventoryList;

    public InventoryAdapter(ArrayList<ItemInventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inventory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemInventory item = inventoryList.get(position);
        holder.textViewSKU.setText("SKU: " + item.getSKU());
        holder.textViewItemName.setText("Name: " + item.getItemName());
        holder.textViewStock.setText("Stock: " + item.getStock());
        holder.textViewUnit.setText("Unit: " + item.getUnit());
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewSKU;
        public TextView textViewItemName;
        public TextView textViewStock;
        public TextView textViewUnit;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewSKU = itemView.findViewById(R.id.textViewSKU);
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            textViewStock = itemView.findViewById(R.id.textViewStock);
            textViewUnit = itemView.findViewById(R.id.textViewUnit);
        }
    }
}
