package com.example.lifesaver.Signups;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.Logins.LoginPageUser;
import com.example.lifesaver.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpForUser extends AppCompatActivity {

    TextView CreateAccountt;
    Button SignUpForUser;
    EditText UserNameOfUserSignUpForUser, EmailOfUserSignUpForUser, NumberOfUserSignUpForUser, PasswordOfUserSignUpForUser;
    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_for_user);
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        UserNameOfUserSignUpForUser = findViewById(R.id.username_in_User_Signup);
        EmailOfUserSignUpForUser = findViewById(R.id.Email_in_User_Signup);
        NumberOfUserSignUpForUser = findViewById(R.id.Number_in_User_Signup);
        PasswordOfUserSignUpForUser = findViewById(R.id.Password_in_User_Signup);
        CreateAccountt = findViewById(R.id.CreateAccount_for_user_signup);

        CreateAccountt.setOnClickListener(v -> {
            Intent i = new Intent(SignUpForUser.this, LoginPageUser.class);
            startActivity(i);
        });

        SignUpForUser = findViewById(R.id.SignUpForDriver);
        SignUpForUser.setOnClickListener(v -> {
            String Email = EmailOfUserSignUpForUser.getText().toString();
            String password = PasswordOfUserSignUpForUser.getText().toString();
            if (UserNameOfUserSignUpForUser.length() == 0) {
                Toast.makeText(this, "Please Enter Username..", Toast.LENGTH_SHORT).show();
            } else if (EmailOfUserSignUpForUser.length() == 0) {
                Toast.makeText(this, "Please Enter Email..", Toast.LENGTH_SHORT).show();
            } else if (NumberOfUserSignUpForUser.length() == 0) {
                Toast.makeText(this, "Please Enter Number..", Toast.LENGTH_SHORT).show();
            } else if (PasswordOfUserSignUpForUser.length() == 0) {
                Toast.makeText(this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
            }
            try {
                firebaseAuth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(SignUpForUser.this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpForUser.this, "Successfully inserted..", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpForUser.this, "Insertion Failed..", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception exception) {
                exception.getLocalizedMessage();
            }
        });
    }
}