package com.example.myfirsrtpro;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalTime;
import java.util.ArrayList;

public class EventEditActivity extends AppCompatActivity implements View.OnClickListener {


    private String myHour;
    private String myMinute;
    private String mySecond;
    private String myMonth;
    private String myYear;
    private String myDay;

    private LocalTime time;
    private ArrayList<Event> firebaseEvents;

    EditText editTextEventName;
    TextView textViewEventDate, textViewEventTime, saveTv;

    //gets instance of authentication project in FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time DataBase in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");

    private EventAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_activity);
        //initialize widgets

        editTextEventName = findViewById(R.id.editTextEventName);
        textViewEventDate = findViewById(R.id.textViewEventDate);
        textViewEventTime = findViewById(R.id.textViewEventTime);
        saveTv = findViewById(R.id.textView4);

        time = LocalTime.now();
        textViewEventDate.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        textViewEventTime.setText("Time: " + CalendarUtils.formattedTime(time));


        firebaseEvents = new ArrayList<Event>();
        myAdapter = new EventAdapter(getApplicationContext(), R.layout.event_cell, firebaseEvents);

        myHour = String.valueOf(time.getHour());
        myMinute = String.valueOf(time.getMinute());
        mySecond = String.valueOf(time.getSecond());


        myDay = String.valueOf(CalendarUtils.selectedDate.getDayOfMonth());
        myMonth = String.valueOf(CalendarUtils.selectedDate.getMonth());
        myYear = String.valueOf(CalendarUtils.selectedDate.getYear());


        saveTv.setOnClickListener(this);
    }


    public void saveEventAction(View view) {

        String eventName = editTextEventName.getText().toString();
        // Write a message to the database
        String UID = mFirebaseAuth.getUid();
        //build a ref for user related data in real time DataBase using user id
        //getReference returns root - the path is users / all (for me )
        DatabaseReference myRef = database.getReference("events/" + UID);

        //adds an item to the FB under the reference specified
        //building objects to my date and time classes
        MyTime myTime1 = new MyTime(myHour, myMinute, mySecond);
        MyDate myDate1 = new MyDate(myDay, myMonth, myYear);
        String myDate1Str = myDate1.getDay() + "/" + myDate1.getMonth() + "/" + myDate1.getYear();
        String myTime1Str = myTime1.getHour() + ":" + myTime1.getMinute() + ":" + myTime1.getSecond();
        Event event1 = new Event(myTime1Str, myDate1Str, eventName);
        String key = myRef.push().getKey();
        myRef = database.getReference("events/" + UID + "/" + key);
        event1.setKey(key);

        myRef.setValue(event1); //push the object to the firebase data sets
        firebaseEvents.add(event1);
        myAdapter.notifyDataSetChanged();

        finish();


    }


        @Override
        public void onClick (View view){
            saveEventAction(view);
        }
    }

