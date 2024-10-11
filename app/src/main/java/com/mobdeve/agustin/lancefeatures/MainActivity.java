package com.mobdeve.agustin.lancefeatures;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    // private CoffeeAdapter adapter;
    // private ArrayList<Coffee> coffeeList;
    // private TextView itemTotal;
    private TextView itemList, itemTotal;
    private CheckBox americano, cafeMocha, vanillaLatte;
    private ArrayList<ItemInventory> inventoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        coffeeList = new ArrayList<>();
//
//// Add coffee items
//        coffeeList.add(new Coffee("Americano", 120));
//        coffeeList.add(new Coffee("Cafe Mocha", 150));
//        coffeeList.add(new Coffee("Vanilla Latte", 160));
//
//        adapter = new CoffeeAdapter(coffeeList, total -> {
//            // Handle total price update here if needed
//        });
//        recyclerView.setAdapter(adapter);

        itemList = findViewById(R.id.itemList);
        itemTotal = findViewById(R.id.itemTotal);
        americano = findViewById(R.id.americano);
        cafeMocha = findViewById(R.id.cafemocha);
        vanillaLatte = findViewById(R.id.vanillalatte);

    }


    public void orderSelection(View view) {
        StringBuilder selectedItems = new StringBuilder("Item/s:\n");
        int total = 0; // Initialize total price

        boolean hasSelection = false; // Track if any items are selected

        if (americano.isChecked()) {
            selectedItems.append("Americano - 120\n");
            total += 120; // Add to total
            hasSelection = true; // At least one item is selected
        }
        if (cafeMocha.isChecked()) {
            selectedItems.append("Cafe Mocha - 150\n");
            total += 150; // Add to total
            hasSelection = true; // At least one item is selected
        }
        if (vanillaLatte.isChecked()) {
            selectedItems.append("Vanilla Latte - 160\n");
            total += 160; // Add to total
            hasSelection = true; // At least one item is selected
        }

        if (hasSelection) {
            itemList.setText(selectedItems.toString());
            itemTotal.setText("Total: " + total); // Update total
        } else {
            itemList.setText("Please Select an Item");
            itemTotal.setText("Total: 0"); // Reset total if no items are selected
        }
    }



    public void clearOrder (View v) {
        americano.setChecked(false);
        cafeMocha.setChecked(false);
        vanillaLatte.setChecked(false);

        itemList.setText("Items:");
        itemTotal.setText("Total:");
    }


    public void accessMyCart(View v) {
        // Create a string for the selected items and total
        StringBuilder selectedItems = new StringBuilder();
        int total = 0;

        if (americano.isChecked()) {
            selectedItems.append("Americano - 120\n");
            total += 120;
        }
        if (cafeMocha.isChecked()) {
            selectedItems.append("Cafe Mocha - 150\n");
            total += 150;
        }
        if (vanillaLatte.isChecked()) {
            selectedItems.append("Vanilla Latte - 160\n");
            total += 160;
        }

        // Create an Intent to start the CartActivity
        Intent intent = new Intent(this, MyCartActivity.class);
        // Pass the selected items and total as extras
        intent.putExtra("selectedItems", selectedItems.toString());
        intent.putExtra("total", total);
        startActivity(intent);
    }


    public void accessMyInventory(View v) {
        Intent i = new Intent(this, InventoryActivity.class);

        inventoryList = new ArrayList<ItemInventory>();
        inventoryList.add(new ItemInventory(1001,"Coffee Beans", 1000, "g"));
        inventoryList.add(new ItemInventory(2001, "Milk", 2000, "ml"));
        inventoryList.add(new ItemInventory(3001,"Simple Syrup", 750 , "ml"));
        inventoryList.add(new ItemInventory(3002, "Chocolate Syrup", 750,"ml"));
        inventoryList.add(new ItemInventory(3003, "Vanilla Syrup", 750, "ml"));
        inventoryList.add(new ItemInventory(4001, "Cups", 100, "pc/s"));
        inventoryList.add(new ItemInventory(4002, "Lids", 100, "pc/s"));

        i.putExtra("inventoryList", inventoryList);

        startActivity(i);
    }

    public void accessTransactions(View v) {
        Intent i = new Intent(this, TransactionsActivity.class);
        startActivity(i);
    }
}