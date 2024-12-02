package com.example.lifesaver.Driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.R;

public class ForceTraveller extends AppCompatActivity {

    CardView accept;
    TextView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_force_traveller);
        getSupportActionBar().setTitle("Traveller");

        add = findViewById(R.id.add);
        add.setOnClickListener(v -> {
            Intent i = new Intent(ForceTraveller.this , AddAmbulance.class);
            startActivity(i);
        });
        accept = findViewById(R.id.Accept_rides);
        accept.setOnClickListener(v -> {
            Intent i = new Intent(ForceTraveller.this , AcceptRide.class);
            startActivity(i);
        });
    }
    @Override
    public void onBackPressed() {
        Dialog dialog = new Dialog(ForceTraveller.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.feedback);
        Button btncancel = dialog.findViewById(R.id.cancel);
        Button btnFeedback = dialog.findViewById(R.id.feedback);
        TextView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(v -> dialog.dismiss());
        btncancel.setOnClickListener(v -> finishAffinity());
        btnFeedback.setOnClickListener(v -> Toast.makeText(ForceTraveller.this, "Thanks For Feedback..", Toast.LENGTH_SHORT).show());
        dialog.show();
    }
}