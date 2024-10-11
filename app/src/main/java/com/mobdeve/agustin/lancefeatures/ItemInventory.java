package com.mobdeve.agustin.lancefeatures;

import java.io.Serializable;

public class ItemInventory implements Serializable {
    private int SKU;
    private String itemName;
    private String unit;
    private int stock;


    // Constructor
    public ItemInventory(int SKU, String itemName, int stock, String unit) {
        this.SKU = SKU;
        this.itemName = itemName;
        this.unit = unit;
        this.stock = stock;
    }

    public int getSKU() {
        return SKU;
    }

    public void setSKU(int SKU) {
        this.SKU = SKU;
    }

    public String getItemName() {
        return itemName;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStock() {
        return stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }


    public void updateStock(int quantity) {
        this.stock += quantity;
    }
}

