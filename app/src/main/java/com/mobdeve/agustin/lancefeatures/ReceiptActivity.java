package com.mobdeve.agustin.lancefeatures;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReceiptActivity extends AppCompatActivity {

    private TextView receiptItems, receiptTotal, receiptDiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receipt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        receiptItems = findViewById(R.id.receiptItems);
        receiptTotal = findViewById(R.id.receiptTotal);
        receiptDiscount = findViewById(R.id.receiptDiscount);

        Intent i = getIntent();
        String orderedItems = i.getStringExtra("cartItems");
        String orderDiscount = i.getStringExtra("cartDiscount");
        String orderTotal = i.getStringExtra("cartTotal");

        receiptItems.setText("Items: \n" + orderedItems);
        receiptTotal.setText("" + orderTotal);
        receiptDiscount.setText("" + orderDiscount);
    }

    public void backBtn (View v) {
        Intent intent = new Intent(ReceiptActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}