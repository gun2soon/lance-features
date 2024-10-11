package com.mobdeve.agustin.lancefeatures;

public class Transactions {
    private String items;
    private double total;
    private String discount;

    public Transactions(String items, double total, String discount) {
        this.items = items;
        this.total = total;
        this.discount = discount;
    }

    public Transactions(String items, double total) {
        this.items = items;
        this.total = total;
    }

    public String getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public String getDiscount() {
        return discount;
    }
}
