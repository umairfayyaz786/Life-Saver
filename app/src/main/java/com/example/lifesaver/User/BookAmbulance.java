package com.example.lifesaver.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.MainActivity;
import com.example.lifesaver.R;

public class BookAmbulance extends AppCompatActivity {

    Spinner select_spinner;
    ImageView amb;
    Animation top, bottom;
    TextView location;
    CardView book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ambulance);
        getSupportActionBar().hide();

        book = findViewById(R.id.book_now);
        book.setOnClickListener(v -> {
            Intent i = new Intent(BookAmbulance.this , ConfirmBooking.class);
            startActivity(i);
        });

        location = findViewById(R.id.Select_location);
        location.setOnClickListener(v -> {
            Intent i = new Intent(BookAmbulance.this , MainActivity.class);
            startActivity(i);
        });

        amb = findViewById(R.id.imageAmbulance);
        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        amb.setAnimation(top);

        select_spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);


        select_spinner.setAdapter(adapter);
        select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Dialog dialog = new Dialog(BookAmbulance.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.feedback);
        Button btncancel = dialog.findViewById(R.id.cancel);
        Button btnFeedback = dialog.findViewById(R.id.feedback);
        TextView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(v -> dialog.dismiss());
        btncancel.setOnClickListener(v -> finishAffinity());
        btnFeedback.setOnClickListener(v -> Toast.makeText(BookAmbulance.this, "Thanks For Feedback..", Toast.LENGTH_SHORT).show());
        dialog.show();
    }
}