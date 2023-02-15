package com.google.play.userexp.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginPassword, loginUsername;
    Button loginButton;
    TextView newUserRegTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.editTextLoginUsername);
        loginPassword = findViewById(R.id.editTextLoginPassword);
        loginButton = findViewById(R.id.loginButton);
        newUserRegTextView = findViewById(R.id.regNewUser);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();

                if(username.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                }

            }
        });

        newUserRegTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Starting a new activity using an explicit intent
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}