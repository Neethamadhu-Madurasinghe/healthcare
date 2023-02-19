package com.google.play.userexp.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView exitCard = findViewById(R.id.cardFDBack);

        exitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        CardView familyPhysicianCard = findViewById(R.id.cardFDFamilyPhysician);
        familyPhysicianCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Family Physicians");
                startActivity(intent);
            }
        });

        CardView dieticianCard = findViewById(R.id.cardFDDietecian);
        dieticianCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Family Physicians");
                startActivity(intent);
            }
        });

        CardView dentistCard = findViewById(R.id.cardFDDentist);
        dentistCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Family Physicians");
                startActivity(intent);
            }
        });

        CardView surgeonCard = findViewById(R.id.cardFDSurgeon);
        surgeonCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Family Physicians");
                startActivity(intent);
            }
        });
    }
}