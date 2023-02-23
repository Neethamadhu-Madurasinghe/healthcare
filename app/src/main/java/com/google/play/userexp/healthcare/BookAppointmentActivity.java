package com.google.play.userexp.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        title = findViewById(R.id.textViewAppTitle);
        editText1 = findViewById(R.id.editTextFullName);
        editText2 = findViewById(R.id.editTextAddress);
        editText3 = findViewById(R.id.editTextContactNumber);
        editText4 = findViewById(R.id.editTextFees);

//      Make the text fields not editable !
        editText1.setKeyListener(null);
        editText2.setKeyListener(null);
        editText3.setKeyListener(null);
        editText4.setKeyListener(null);

        Intent intent = getIntent();
        String titleString = intent.getStringExtra("title");
        String fullName = intent.getStringExtra("line1");
        String address = intent.getStringExtra("line2");
        String contact = intent.getStringExtra("line4");
        String payment = intent.getStringExtra("line5");

        title.setText(titleString);
        editText1.setText(fullName);
        editText2.setText(address);
        editText3.setText(contact);
        editText4.setText("Con Fees: " + payment + "/=");





    }
}