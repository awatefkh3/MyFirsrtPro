package com.example.myfirsrtpro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

/*
public class EventEditActivity extends AppCompatActivity {

    private EditText eventNameET;
    private TextView eventDateTV ,eventTimeTV;
    private LocalTime time;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private TimePickerDialog timePickerDialog;
    private Button timeButton;
    private ArrayList<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
    }



    private void initWidgets() {
        eventDateTV = findViewById(R.id.eventDateTV);
        eventNameET = findViewById(R.id.eventNameET);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate,time);
        eventList.add(newEvent);
        finish();
    }


*/
//}
//todo delete this