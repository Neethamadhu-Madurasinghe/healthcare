package com.google.play.userexp.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4;
    TextView title;
    private Button dateButton, timeButton;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

//    This inner class here for make the date picker
//    https://developer.android.com/develop/ui/views/components/pickers#java
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }

//    Time picker
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
    }

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

        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });





    }
}