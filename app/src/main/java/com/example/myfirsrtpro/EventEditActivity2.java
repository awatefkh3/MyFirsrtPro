package com.example.myfirsrtpro;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class EventEditActivity2 extends AppCompatActivity implements View.OnClickListener {


    private String myHour;
    private String myMinute;
    private String mySecond;
    private String myMonth;
    private String myYear;
    private String myDay;

    private LocalTime time;
    private ArrayList<FireBaseEvent> firebaseEvents;

   // private EventAdapter myEventAdapter;


    EditText editTextEventName;
    TextView textViewEventDate,textViewEventTime,saveTv;

    //gets instance of authentication project in FB console
    private FirebaseAuth mFirebaseAuth  = FirebaseAuth.getInstance();
    //gets the root of the real time DataBase in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");

    private FireBaseEventAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_acticity2);
        //initialize widgets

        editTextEventName = findViewById(R.id.editTextEventName);
        textViewEventDate = findViewById(R.id.textViewEventDate);
        textViewEventTime = findViewById(R.id.textViewEventTime);
        saveTv = findViewById(R.id.textView4);

        time = LocalTime.now();
        textViewEventDate.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        textViewEventTime.setText("Time: " + CalendarUtils.formattedTime(time));


        firebaseEvents = new ArrayList<FireBaseEvent>();
        myAdapter = new FireBaseEventAdapter(getApplicationContext(),R.layout.event_cell,firebaseEvents);

        myHour = String.valueOf(time.getHour());
        myMinute = String.valueOf(time.getMinute());
        mySecond = String.valueOf(time.getSecond());


        myDay = String.valueOf(CalendarUtils.selectedDate.getDayOfMonth());
        myMonth= String.valueOf(CalendarUtils.selectedDate.getMonth());
        myYear = String.valueOf(CalendarUtils.selectedDate.getYear());


        saveTv.setOnClickListener(this);
    }

    /*public  void showTimeDialog(View view) {
        final Calendar calendar1 = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                calendar1.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar1.set(Calendar.MINUTE,minute);
                SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
                //editTextEventTime.setText(df.format(new Date()));

                //myTime = new Time(minute,hourOfDay,0);
                myHour = String.valueOf(hourOfDay);
                myMinute = String.valueOf(minute);
                mySecond = "";

                //editTextEventTime.setText(myTime+":"+myHour+":"+myMinute+":"+mySecond);

            }
        };
        new TimePickerDialog(EventEditActivity2.this,timeSetListener,calendar1.get(Calendar.HOUR_OF_DAY),calendar1.get(Calendar.MINUTE),true).show();
    }*/

    public void saveEventAction(View view) {

        //Log.d("TESTING", "TESTING");
        String eventName = editTextEventName.getText().toString();
        //Event newEvent = new Event(eventName,CalendarUtils.selectedDate,time);
        //events.add(newEvent);
       // myEventAdapter.notifyDataSetChanged();

        //String time = editTextEventTime.getText().toString();

       // DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd'T'HH:mm:ss.SSS"));
       // LocalTime ldt = LocalTime.parse(time,dtf);
        //Event newEvent = new Event(eventName, CalendarUtils.selectedDate,ldt);
        //Event.eventList.add(newEvent);
        // Write a message to the database
        String UID  = mFirebaseAuth.getUid();
        //build a ref for user related data in real time DataBase using user id
        DatabaseReference myRef = database.getReference("events/"+UID);
        //getReference returns root - the path is users / all (for me )

        //todo this
        //adds an item to the FB under the reference specified
        //building objects to my date and time classes
        MyTime myTime1 = new MyTime(myHour,myMinute,mySecond);
        MyDate myDate1 = new MyDate(myDay,myMonth,myYear);
        String myDate1Str = myDate1.getDay()+"/"+myDate1.getMonth()+"/"+myDate1.getYear();
        String myTime1Str = myTime1.getHour()+":"+myTime1.getMinute()+":"+myTime1.getSecond();
        FireBaseEvent event1 = new FireBaseEvent(myTime1Str,myDate1Str,eventName);
        myRef.push().setValue(event1); //push the object to the firebase data sets
        firebaseEvents.add(event1);
        myAdapter.notifyDataSetChanged();

        finish();

        }



       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //for each,children are the objects
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    FireBaseEvent event1 = dataSnapshot.getValue(FireBaseEvent.class);

                    //myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/





    /*private Date stringToDate(String aDate, String aFormat) {

        if (aDate == null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;
    }
*/

    @Override
    public void onClick(View view) {
            saveEventAction(view);
        }
}