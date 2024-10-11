package com.mobdeve.agustin.lancefeatures;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MyCartActivity extends AppCompatActivity {

    private TextView cartItems, cartTotal, cartDiscount;
    private EditText voucherCode;
    private Button voucherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart); // Make sure to set the correct layout

        cartItems = findViewById(R.id.myCartItems);
        cartDiscount = findViewById(R.id.myCartDiscount);
        cartTotal = findViewById(R.id.myCartTotal);
        voucherCode = findViewById(R.id.itemVoucher);
        voucherBtn = findViewById(R.id.voucherButton);

        // Get the Intent that started this activity
        Intent intent = getIntent();
        String selectedItems = intent.getStringExtra("selectedItems");
        int total = intent.getIntExtra("total", 0);

        // Update the TextViews with the received data
        cartItems.setText(selectedItems);
        cartDiscount.setText("Discount: ");
        cartTotal.setText("Total: " + total);
    }

    public void voucherButton(View view) {
        String code = voucherCode.getText().toString();

        // Extract just the numeric part from the cartTotal (e.g., "Total: 100.0" to "100.0")
        String stringTotal = cartTotal.getText().toString();
        String numericPart = stringTotal.replaceAll("[^\\d.]", ""); // Removes non-numeric characters except for "."

        Double total = Double.parseDouble(numericPart); // Convert the numeric part to Double
        Double discount = 0.0;
        Double newTotal = total;

        if (code.equals("LUCKY10")) {
            discount = total * 0.10; // 10% discount
            newTotal = total - discount;
        } else if (code.equals("DISABLED20")) {
            discount = total * 0.20; // PWD/Senior 20% discount
            newTotal = total - discount;
        } else if (code.equals("HESOYAM")) {
            discount = total;
            newTotal = 0.0; // Drink Becomes Free
        } else {
            voucherCode.setText("Please enter a valid code");
            return; // Exit the method if the code is invalid
        }

        // Update the cartTotal TextView to reflect the new total
        cartDiscount.setText("Discount: " + discount);
        cartTotal.setText("Total: " + newTotal);
    }

    public void checkout(View v) {
        Intent i = new Intent(this, ReceiptActivity.class);

        // Extract the numeric part again for the transaction
        String stringTotal = cartTotal.getText().toString();
        String numericPart = stringTotal.replaceAll("[^\\d.]", ""); // Removes non-numeric characters

        // Create the transaction object to add to the TransactionManager
        Transactions newTransaction = new Transactions(cartItems.getText().toString(),
                Double.parseDouble(numericPart), // Using numericPart here
                cartDiscount.getText().toString());

        TransactionManager.getInstance().addTransaction(newTransaction); // Add to transaction manager

        // Pass data to ReceiptActivity
        i.putExtra("cartItems", cartItems.getText().toString());
        i.putExtra("cartDiscount", cartDiscount.getText().toString());
        i.putExtra("cartTotal", cartTotal.getText().toString());
        startActivity(i);
    }
}
