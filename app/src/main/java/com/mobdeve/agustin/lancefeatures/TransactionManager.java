package com.mobdeve.agustin.lancefeatures;

import java.util.ArrayList;

public class TransactionManager {
    private static TransactionManager instance;
    private ArrayList<Transactions> transactions; // Using your Transactions class

    private TransactionManager() {
        transactions = new ArrayList<>();
    }

    public static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    public void addTransaction(Transactions transaction) {
        transactions.add(transaction);
    }

    public ArrayList<Transactions> getTransactions() {
        return transactions;
    }
}

