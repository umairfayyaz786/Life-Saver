package com.example.lifesaver.Logins;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.User.BookAmbulance;
import com.example.lifesaver.R;
import com.example.lifesaver.Signups.SignUpForUser;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageUser extends AppCompatActivity {


    TextView CreateAccountUserr;
    ImageView google;
    Button LoginForUser;
    EditText EmailOfUser, PasswordOfUser;
    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page_user);
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();

        google = findViewById(R.id.googleUser);
        google.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
        ));

        EmailOfUser = findViewById(R.id.username);
        PasswordOfUser = findViewById(R.id.password);
        CreateAccountUserr = findViewById(R.id.CreateAccountForUser);

        CreateAccountUserr.setOnClickListener(v -> {
            Intent i = new Intent(LoginPageUser.this, SignUpForUser.class);
            startActivity(i);
        });

        LoginForUser = findViewById(R.id.login_for_User);
        LoginForUser.setOnClickListener(v -> {
            String username = EmailOfUser.getText().toString();
            String password = PasswordOfUser.getText().toString();

            if (EmailOfUser.length() == 0) {
                Toast.makeText(this, "Please Enter Username..", Toast.LENGTH_SHORT).show();
            } else if (PasswordOfUser.length() == 0) {
                Toast.makeText(this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
            } else if (EmailOfUser.length() == 0 || PasswordOfUser.length() == 0) {
                Toast.makeText(this, "Please Filled Empty Field..", Toast.LENGTH_SHORT).show();
            }
            try {
                firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(LoginPageUser.this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginPageUser.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext() , BookAmbulance.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginPageUser.this, "Login Failed..", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception exception) {
                exception.getLocalizedMessage();
            }
        });
    }
}