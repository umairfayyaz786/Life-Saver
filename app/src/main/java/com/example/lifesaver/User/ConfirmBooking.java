package com.example.lifesaver.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifesaver.R;

public class ConfirmBooking extends AppCompatActivity {

    ImageView amb;
    Animation top, bottom;
    TextView confirmbooking ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Confirm Booking");

//        int selectedItemIndex = getIntent().getIntExtra("SPINNER_ITEM", 0);
//        yourSpinnerInAnotherActivity.setSelection(selectedItemIndex);

        confirmbooking = findViewById(R.id.Confirm_Booking);
        amb = findViewById(R.id.ConfirmBookingAmbulance);
        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        amb.setAnimation(top);
        confirmbooking.setAnimation(bottom);

        confirmbooking.setOnClickListener(v -> {
            Intent i = new Intent(ConfirmBooking.this , TrackAmbulance.class);
            startActivity(i);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent p = new Intent(ConfirmBooking.this , BookAmbulance.class);
            startActivity(p);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent p = new Intent(ConfirmBooking.this , BookAmbulance.class);
        startActivity(p);
        super.onBackPressed();
    }
}
