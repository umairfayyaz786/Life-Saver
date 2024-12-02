package com.example.lifesaver.Driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lifesaver.R;

public class AddAmbulance extends AppCompatActivity {

    Button adds;
    EditText AmbRegister , AmbModel , Ambphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_for_driver);
        setContentView(R.layout.activity_add_ambulance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Ambulance");

        Ambphone = findViewById(R.id.Ambphone);
        AmbRegister = findViewById(R.id.AmbRegister);
        AmbModel = findViewById(R.id.AmbModel);

     adds=findViewById(R.id.addAMb);
        adds.setOnClickListener(v -> {
            if (Ambphone.length() == 0 || AmbModel.length() == 0 || AmbRegister.length() == 0 ){
                Toast.makeText(this, "Please Filled Empty Fields..", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Ambulance add successfully !!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddAmbulance.this , ForceTraveller.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent p = new Intent(AddAmbulance.this , ForceTraveller.class);
            startActivity(p);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent p = new Intent(AddAmbulance.this , ForceTraveller.class);
        startActivity(p);
        super.onBackPressed();
    }
}