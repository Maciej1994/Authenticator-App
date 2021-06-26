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

public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginButton;
    TextView mCreateBar;
    ProgressBar progressBar;
    FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.editTextTextPersonName3);
        mPassword = findViewById(R.id.editTextTextPersonName2);
        progressBar = findViewById(R.id.progressBar2);
        FAuth = FirebaseAuth.getInstance();
        mLoginButton = findViewById(R.id.button);
        mCreateBar = findViewById(R.id.editTextTextPersonName4);


               mLoginButton.setOnClickListener((v)-> {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is reguired!");
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


                    FAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((task)-> {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }


                });

        });


            mCreateBar.setOnClickListener((v)-> {
                startActivity(new Intent(getApplicationContext(), Register.class));

        });
    }
}
