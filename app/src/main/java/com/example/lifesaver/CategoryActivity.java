package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.lifesaver.Logins.LoginPageDriver;
import com.example.lifesaver.Logins.LoginPageUser;

public class CategoryActivity extends AppCompatActivity {

    CardView Driver , User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category);

        Driver=findViewById(R.id.driverCard);
        User=findViewById(R.id.UserCard);

        Driver.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext() , LoginPageDriver.class);
            startActivity(i);
        });

        User.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext() , LoginPageUser.class);
            startActivity(i);
        });
    }
}