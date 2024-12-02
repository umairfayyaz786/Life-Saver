package com.example.lifesaver.Signups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.Logins.LoginPageDriver;
import com.example.lifesaver.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpForDriver extends AppCompatActivity {

    TextView CreateAccountt;
    Button SignUpForDriver;
    EditText UserNameOfUserSignUpForDriver , EmailOfUserSignUpForDriver ,NumberOfUserSignUpForDriver , PasswordOfUserSignUpForDriver;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    String id;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_for_driver);
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        UserNameOfUserSignUpForDriver=findViewById(R.id.username_for_signup_driver);
        EmailOfUserSignUpForDriver=findViewById(R.id.Email_for_signup_driver);
        NumberOfUserSignUpForDriver=findViewById(R.id.Number_for_signup_driver);
        PasswordOfUserSignUpForDriver=findViewById(R.id.Password_for_signup_driver);
        CreateAccountt=findViewById(R.id.CreateAccount);

        CreateAccountt.setOnClickListener(v -> {
            Intent i = new Intent(SignUpForDriver.this , LoginPageDriver.class);
            startActivity(i);
        });

        SignUpForDriver = findViewById(R.id.SignUpForDriver);
        SignUpForDriver.setOnClickListener(v -> {
            String Email = EmailOfUserSignUpForDriver.getText().toString();
            String password = PasswordOfUserSignUpForDriver.getText().toString();
            String username = UserNameOfUserSignUpForDriver.getText().toString();
            String number = NumberOfUserSignUpForDriver.getText().toString();

            if (UserNameOfUserSignUpForDriver.length() == 0 && !UserNameOfUserSignUpForDriver.equals("null") && !TextUtils.isEmpty(username)){
                Toast.makeText(this, "Please Enter Username..", Toast.LENGTH_SHORT).show();
            }
            else if(EmailOfUserSignUpForDriver.length() == 0 && !EmailOfUserSignUpForDriver.equals("null") && !TextUtils.isEmpty(Email)){
                Toast.makeText(this, "Please Enter Email..", Toast.LENGTH_SHORT).show();
            }
            else if(NumberOfUserSignUpForDriver.length() == 0 && !NumberOfUserSignUpForDriver.equals("null") && !TextUtils.isEmpty(number)) {
                Toast.makeText(this, "Please Enter Number..", Toast.LENGTH_SHORT).show();
            }
            else if(PasswordOfUserSignUpForDriver.length() == 0 && !PasswordOfUserSignUpForDriver.equals("null") && !TextUtils.isEmpty(password)){
                Toast.makeText(this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
            }
            try {
            firebaseAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(SignUpForDriver.this, task -> {
                if (task.isSuccessful()){
                    id=firebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fstore.collection("data").document(id);
                    Map<String, Object> user =new HashMap<>();
                    user.put("username" , username);
                    user.put("email" , Email);
                    user.put("Number" , number);
                    user.put("password" , password);
                    documentReference.set(user).addOnSuccessListener(com.example.lifesaver.Signups.SignUpForDriver.this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SignUpForDriver.this, "Successfully inserted..", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(com.example.lifesaver.Signups.SignUpForDriver.this , LoginPageDriver.class);
                            startActivity(i);
                        }
                    });
                }else {
                    Toast.makeText(SignUpForDriver.this, "Insertion Failed..", Toast.LENGTH_SHORT).show();
                }
            });
            }catch (Exception exception){
                exception.getLocalizedMessage();
            }
        });
    }
}