package com.example.authenticatorapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mRegisterButton;
    TextView mLoginButton;
    FirebaseAuth FAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.editTextTextPersonName);
        mPassword = findViewById(R.id.editTextTextPersonName2);
        progressBar = findViewById(R.id.progressBar);
        FAuth = FirebaseAuth.getInstance();
        mRegisterButton = findViewById(R.id.button);
        mLoginButton = findViewById(R.id.editTextTextPersonName4);


        progressBar = findViewById(R.id.progressBar);

        if(FAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterButton.setOnClickListener((v) -> {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Emial is reguired!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Wrong password!");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be 6 or more characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                FAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task)-> {

                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }


                });

        });

        mLoginButton.setOnClickListener((v)-> {
                startActivity(new Intent(getApplicationContext(), Login.class));

        });
    }
}





