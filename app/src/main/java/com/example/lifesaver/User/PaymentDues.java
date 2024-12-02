package com.example.lifesaver.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lifesaver.R;

public class PaymentDues extends AppCompatActivity {

    TextView confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_dues);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(v -> {
            Intent o = new Intent(PaymentDues.this , RecipientActivity.class);
            startActivity(o);
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent p = new Intent(PaymentDues.this , PaymentMethod.class);
            startActivity(p);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent p = new Intent(PaymentDues.this , PaymentMethod.class);
        startActivity(p);
        super.onBackPressed();
    }
}