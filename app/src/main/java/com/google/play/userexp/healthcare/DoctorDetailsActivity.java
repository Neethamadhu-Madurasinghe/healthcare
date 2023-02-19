package com.google.play.userexp.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 = {
            {"Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 0719532122", "600"},
            {"Doctor Name: Prasad Pwar", "Hospital Address: Pune", "Exp: 15yrs", "Mobile No: 0719532123", "650"},
            {"Doctor Name: Ashok Panda", "Hospital Address: Pimpri", "Exp: 10yrs", "Mobile No: 0719532124", "700"},
    };

    private String[][] doctor_details2 = {
            {"Doctor Name: Saman Kumara", "Hospital Address: Gampaha", "Exp: 12yrs", "Mobile No: 0719532150", "700"},
            {"Doctor Name: Chithral Somapala", "Hospital Address: Colombo", "Exp: 5yrs", "Mobile No: 071953221", "900"},
            {"Doctor Name: Ashok Panda", "Hospital Address: Pimpri", "Exp: 10yrs", "Mobile No: 0719532124", "700"},
    };

    TextView titleView;
    Button backButton;
    String[][] doctor_details = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details_activity);

        titleView = findViewById(R.id.textViewODTitle);
        backButton = findViewById(R.id.buttonBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        titleView.setText(title);

        if(title.compareTo("Family Physicians") == 0) {
            doctor_details = doctor_details1;

        }else {
            doctor_details = doctor_details2;
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });


    }
}