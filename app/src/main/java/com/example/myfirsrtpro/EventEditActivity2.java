package com.example.myfirsrtpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EventEditActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Time myTime;
    EditText editTextEventTime,editTextEventName;
    TextView textViewEventDate, saveTv;
    //gets instance of authentication project in FB console
    private FirebaseAuth mFirebaseAuth  = FirebaseAuth.getInstance();
    //gets the root of the real time DataBase in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_acticity2);
        //initialize widgets
        editTextEventTime = findViewById(R.id.editTextEventTime);
        editTextEventName = findViewById(R.id.editTextEventName);
        textViewEventDate = findViewById(R.id.textViewEventDate);
        saveTv = findViewById(R.id.textView4);

        textViewEventDate.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));

        saveTv.setOnClickListener(this);
        editTextEventTime.setOnClickListener(this);
    }

    public  void showTimeDialog(View view) {
        final Calendar calendar1 = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                calendar1.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar1.set(Calendar.MINUTE,minute);
                SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
                editTextEventTime.setText(df.format(new Date()));

                myTime = new Time(minute,hourOfDay,0);
            }
        };
        new TimePickerDialog(EventEditActivity2.this,timeSetListener,calendar1.get(Calendar.HOUR_OF_DAY),calendar1.get(Calendar.MINUTE),true).show();
    }

    public void saveEventAction(View view) {

        Log.d("TESTING", "TESTING");
        String eventName = editTextEventName.getText().toString();
        String time = editTextEventTime.getText().toString();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd'T'HH:mm:ss.SSS"));
        LocalTime ldt = LocalTime.parse(time,dtf);
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate,ldt);
        Event.eventList.add(newEvent);
        // Write a message to the database
        String UID  = mFirebaseAuth.getUid();
        //build a ref for user related data in real time DataBase using user id
        DatabaseReference myRef = database.getReference("users/"+UID);
        //getReference returns root - the path is users / all (for me )

        //todo this
        //adds an item to the FB under the reference specified
        Event event1 = new Event(eventName,textViewEventDate.getText().toString(),myTime.toString());
        myRef.push().setValue(event1); //put the object

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //for each,children are the objects
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Event event1 = dataSnapshot.getValue(Event.class);
                    //list.add(item1);--> add to the arrayList
                    //myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        finish();
    }

    private Date stringToDate(String aDate, String aFormat) {

        if (aDate == null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;
    }


    @Override
    public void onClick(View view) {
        if(view == editTextEventTime){
            showTimeDialog(view);
        }else {
            saveEventAction(view);
        }
    }
}