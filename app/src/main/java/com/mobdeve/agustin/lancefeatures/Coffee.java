package com.mobdeve.agustin.lancefeatures;

public class Coffee {
    private String name;
    private int count;
    private final int price;

    public Coffee(String name, int price) {
        this.name = name;
        this.count = 0;
        this.price = price;
    }

    public void incrementCount() {
        count++;
    }

    public int decrementCount() {
        if (count > 0) {
            count--;
        }
        return count;
    }

    public int getTotalPrice() {
        return count * price;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

