package com.example.lifesaver.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lifesaver.R;

public class PaymentMethod extends AppCompatActivity {

    TextView continu;
    CardView cardpay , paypal , cash;
    RadioButton cardpayRadio , paypalRadio , cashRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment Method");

        cardpay = findViewById(R.id.cardPayment);
        paypal = findViewById(R.id.paypalPayment);
        cash = findViewById(R.id.cashPayment);
        cardpayRadio = findViewById(R.id.creditRadio);
        paypalRadio = findViewById(R.id.paypalRadio);
        cashRadio = findViewById(R.id.cashRadio);

        cardpay.setOnClickListener(v -> {
            cardpayRadio.setChecked(true);
            paypalRadio.setChecked(false);
            cashRadio.setChecked(false);
        });
        paypal.setOnClickListener(v -> {
            paypalRadio.setChecked(true);
            cardpayRadio.setChecked(false);
            cashRadio.setChecked(false);
        });
        cash.setOnClickListener(v -> {
            cashRadio.setChecked(true);
            paypalRadio.setChecked(false);
            cardpayRadio.setChecked(false);
        });

        continu = findViewById(R.id.cont);
        continu.setOnClickListener(v -> {
            Intent o = new Intent(PaymentMethod.this , PaymentDues.class);
            startActivity(o);
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent p = new Intent(PaymentMethod.this , TrackAmbulance.class);
            startActivity(p);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent p = new Intent(PaymentMethod.this , TrackAmbulance.class);
        startActivity(p);
        super.onBackPressed();
    }
}