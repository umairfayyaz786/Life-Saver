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

import com.example.lifesaver.Driver.ForceTraveller;
import com.example.lifesaver.R;
import com.example.lifesaver.Signups.SignUpForDriver;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageDriver extends AppCompatActivity {

    ImageView google;
    TextView CreateAccountDriverr;
    Button LoginForDriver;
    EditText UserNameOfDriver , PasswordOfDriver;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().hide();

        google = findViewById(R.id.googleDriver);
        google.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
        ));

        firebaseAuth = FirebaseAuth.getInstance();
        UserNameOfDriver=findViewById(R.id.username);
        PasswordOfDriver=findViewById(R.id.password);
        CreateAccountDriverr=findViewById(R.id.CreateAccountDriver);

        CreateAccountDriverr.setOnClickListener(v -> {
            Intent i = new Intent(LoginPageDriver.this , SignUpForDriver.class);
            startActivity(i);
        });

        LoginForDriver = findViewById(R.id.login_for_driver);
        LoginForDriver.setOnClickListener(v -> {

            String username = UserNameOfDriver.getText().toString();
            String password = PasswordOfDriver.getText().toString();

            if (UserNameOfDriver.length() == 0){
                Toast.makeText(this, "Please Enter Username..", Toast.LENGTH_SHORT).show();
            }
            else if(PasswordOfDriver.length() == 0){
                Toast.makeText(this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
            }
            else if (UserNameOfDriver.length() == 0 ||PasswordOfDriver.length() == 0){
                Toast.makeText(this, "Please Filled Empty Field..", Toast.LENGTH_SHORT).show();
            }
            try {
                firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(LoginPageDriver.this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginPageDriver.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginPageDriver.this , ForceTraveller.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginPageDriver.this, "Login Failed..", Toast.LENGTH_SHORT).show();
                    }
                });

            }catch (Exception exception){
                exception.getLocalizedMessage();
            }
        });
    }
}