package com.example.myfirsrtpro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class EventActicity2 extends AppCompatActivity {

    EditText editTextEventTime,editTextEventName;
    TextView textViewEventDate;
    Button timePickerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_acticity2);
        //initialize widgets
        editTextEventTime = findViewById(R.id.editTextEventTime);
        editTextEventName = findViewById(R.id.editTextEventName);
        textViewEventDate = findViewById(R.id.textViewEventDate);
        textViewEventDate.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));

       /* editTextEventTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog(editTextEventTime);
            }
        });*/
    }

    public  void showTimeDialog(View view) {
        final Calendar calendar1 = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                calendar1.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar1.set(Calendar.MINUTE,minute);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                editTextEventTime.setText(sdf.format(calendar1));
            }
        };
            new TimePickerDialog(EventActicity2.this,timeSetListener,calendar1.get(Calendar.HOUR_OF_DAY),calendar1.get(Calendar.MINUTE),true).show();
    }

    public void saveEventAction(View view) {
        String eventName = editTextEventName.getText().toString();
        String time = editTextEventTime.getText().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime ldt = LocalTime.parse(time,dtf);
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate,ldt);
        Event.eventList.add(newEvent);
        finish();
    }

}