package com.example.lifesaver.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.lifesaver.R;

public class RecipientActivity extends AppCompatActivity {

    ImageView cls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recipient);
        getSupportActionBar().hide();

        cls = findViewById(R.id.close);
        cls.setOnClickListener(v -> {
            Intent p = new Intent(RecipientActivity.this , BookAmbulance.class);
            startActivity(p);
        });

    }
}