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
import android.widget.Toast;

import com.example.lifesaver.MainActivity;
import com.example.lifesaver.R;

public class TrackAmbulance extends AppCompatActivity {

    ImageView image , PhoneIcon;
    Animation top, bottom;
    TextView CompletePayment , DriverAllocated , SanitisedAmbulance , trackAmbulancee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_ambulance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Track Ambulance");

        trackAmbulancee = findViewById(R.id.trackAmbulance);
        trackAmbulancee.setOnClickListener(v -> {
            Intent i = new Intent(TrackAmbulance.this , MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "Track Your Ambulance here..", Toast.LENGTH_SHORT).show();
        });
        CompletePayment = findViewById(R.id.completePayment);
        image = findViewById(R.id.ConfirmBookingAmbulanceTrack);
        SanitisedAmbulance = findViewById(R.id.sanitisedAmbulance);
        PhoneIcon = findViewById(R.id.phoneIcon);
        DriverAllocated = findViewById(R.id.driverAllocated);
        image = findViewById(R.id.ConfirmBookingAmbulanceTrack);
        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        image.setAnimation(top);
        DriverAllocated.setAnimation(top);
        SanitisedAmbulance.setAnimation(top);
        PhoneIcon.setAnimation(bottom);
        CompletePayment.setAnimation(bottom);


        CompletePayment.setOnClickListener(v -> {
            Intent i = new Intent(TrackAmbulance.this , PaymentMethod.class);
            startActivity(i);
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent p = new Intent(TrackAmbulance.this , ConfirmBooking.class);
            startActivity(p);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent p = new Intent(TrackAmbulance.this , ConfirmBooking.class);
        startActivity(p);
        super.onBackPressed();
    }
}