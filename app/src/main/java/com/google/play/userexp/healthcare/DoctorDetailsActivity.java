package com.google.play.userexp.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoctorDetailsActivity extends AppCompatActivity {

    TextView titleView;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details_activity);

        titleView = findViewById(R.id.textViewODTitle);
        backButton = findViewById(R.id.buttonBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        titleView.setText(title);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });


    }
}