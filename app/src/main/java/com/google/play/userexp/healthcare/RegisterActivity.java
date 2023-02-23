package com.google.play.userexp.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText registerUsername, registerEmail, registerPassword, registerConfirmPassword;
    Button registerButton;
    TextView existingUser;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        HealthcareApplication healthcareApp = (HealthcareApplication) getApplication();
        db = Database.getInstance(getApplicationContext(), healthcareApp.executorService, healthcareApp.mainThreadHandler);

        registerUsername = findViewById(R.id.editTextFullName);
        registerEmail = findViewById(R.id.editTextAddress);
        registerPassword = findViewById(R.id.editTextContactNumber);
        registerConfirmPassword = findViewById(R.id.editTextFees);
        registerUsername = findViewById(R.id.editTextFullName);
        registerButton = findViewById(R.id.registerButton);
        existingUser = findViewById(R.id.textViewExistingUser);

        existingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = registerUsername.getText().toString();
                String password = registerPassword.getText().toString();
                String confirm = registerConfirmPassword.getText().toString();
                String email = registerEmail.getText().toString();

//                Validate
                if(username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();

                } else {
                    if(password.compareTo(confirm) == 0) {
                        if(isValid(password)) {
                            db.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Record added successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Password is too weak", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Password and confirm password did not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public static boolean isValid(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}