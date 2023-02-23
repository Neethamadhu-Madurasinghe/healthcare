package com.google.play.userexp.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

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

    private String[][] doctor_details3 = {
            {"Doctor Name: Chithral Sompala", "Hospital Address: Hambanthota", "Exp: 9yrs", "Mobile No: 0719532190", "700"},
            {"Doctor Name: Priyantha Doe", "Hospital Address: kaluthara", "Exp: 5yrs", "Mobile No: 071953221", "900"},
            {"Doctor Name: Ashok Panda", "Hospital Address: Pimpri", "Exp: 10yrs", "Mobile No: 0719532124", "700"},
    };

    private String[][] Doctor_details;
    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter simpleAdapter;

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

        }else if(title.compareTo("Dieticians") == 0) {
            doctor_details = doctor_details2;

        }else {
            doctor_details = doctor_details;
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5","Consultant Fee:" + doctor_details[i][4]+"/=");
            list.add(item);
        }

//         Simple adaptor sends data to a list in the view
        simpleAdapter = new SimpleAdapter(
                getApplicationContext(),
                list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
                );


        ListView listView = findViewById(R.id.listViewDoctorDetails);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("line1", doctor_details[i][0]);
                intent.putExtra("line2", doctor_details[i][1]);
                intent.putExtra("line3", doctor_details[i][2]);
                intent.putExtra("line4", doctor_details[i][3]);
                intent.putExtra("line5", doctor_details[i][4]);
                startActivity(intent);
            }
        });
    }
}