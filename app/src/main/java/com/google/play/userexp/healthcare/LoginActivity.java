package com.google.play.userexp.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    public EditText loginPassword, loginUsername;
    public Button loginButton;
    public TextView newUserRegTextView;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        HealthcareApplication healthcareApp = (HealthcareApplication) getApplication();
        db = Database.getInstance(getApplicationContext(), healthcareApp.executorService, healthcareApp.mainThreadHandler);

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
                    loginButton.setEnabled(false);
                    db.login(username, password, new DatabaseResponse<Boolean>() {
                        @Override
                        public void onComplete(Boolean result) {
                            if(result) {
                                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", username);
                                editor.apply();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                            }else {
                                Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                            }
                            loginButton.setEnabled(true);
                        }
                    });

//                    if(db.login(username, password)) {
//                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
//                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("username", username);
//                        editor.apply();
//                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    }else {
//
//                    }

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