package com.mobdeve.agustin.lancefeatures;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ItemInventory> inventoryList;
    private InventoryAdapter adapter;

    private Spinner skuSpinner;
    private EditText updateStockEditText;
    private Button updateStockButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the passed inventoryList from the Intent
        inventoryList = (ArrayList<ItemInventory>) getIntent().getSerializableExtra("inventoryList");

        // Set up the adapter
        adapter = new InventoryAdapter(inventoryList);
        recyclerView.setAdapter(adapter);

        // Initialize Spinner and other views
        skuSpinner = findViewById(R.id.skuSpinner);
        updateStockEditText = findViewById(R.id.updateStockEditText);
        updateStockButton = findViewById(R.id.updateStockButton);

        // Populate the SKU spinner with SKUs from inventoryList
        ArrayList<String> skuList = new ArrayList<>();
        for (ItemInventory item : inventoryList) {
            skuList.add(String.valueOf(item.getSKU()));
        }
        ArrayAdapter<String> skuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skuList);
        skuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skuSpinner.setAdapter(skuAdapter);

        // Set up button click to update stock
        updateStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStock();
            }
        });
    }

    private void updateStock() {
        // Get selected SKU
        String selectedSKU = skuSpinner.getSelectedItem().toString();

        // Get entered stock value
        String newStockStr = updateStockEditText.getText().toString();

        if (!newStockStr.isEmpty()) {
            int newStock = Integer.parseInt(newStockStr);

            // Find the item by SKU and update its stock
            for (ItemInventory item : inventoryList) {
                if (String.valueOf(item.getSKU()).equals(selectedSKU)) {
                    item.setStock(newStock); // Update stock
                    adapter.notifyDataSetChanged(); // Notify adapter to refresh RecyclerView
                    Toast.makeText(InventoryActivity.this, "Stock updated!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        } else {
            Toast.makeText(this, "Please enter a valid stock value", Toast.LENGTH_SHORT).show();
        }
    }
}

