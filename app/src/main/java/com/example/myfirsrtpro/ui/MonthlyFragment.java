package com.example.myfirsrtpro.ui;


import static com.example.myfirsrtpro.CalendarUtils.daysInMonthArray;
import static com.example.myfirsrtpro.CalendarUtils.monthYearFromDate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirsrtpro.CalendarAdapter;
import com.example.myfirsrtpro.CalendarUtils;
import com.example.myfirsrtpro.NotificationReceiver;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.databinding.FragmentMonthlyBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.ArrayList;

public class MonthlyFragment extends Fragment implements CalendarAdapter.OnItemListener, View.OnClickListener{

    private FragmentMonthlyBinding binding;
    private static final int NOTIFICATION_REMINDER_NIGHT = 1;
    //gets instance of authentication project in FB console
    private FirebaseAuth mFirebaseAuth  = FirebaseAuth.getInstance();
    //gets the root of the real time DataBase in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");
    private TextView monthYearTV;
    private RecyclerView calendarRecyclerView;
    private Button preButton,nxtButton;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        CalendarUtils.selectedDate = LocalDate.now();



        binding = FragmentMonthlyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //pending intent waiting for the right time
        Intent notifyIntent = new Intent(getContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (getContext(), NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);

        calendarRecyclerView = root.findViewById(R.id.calendarRecyclerView);
        monthYearTV = root.findViewById(R.id.monthYearTV);
        preButton = root.findViewById(R.id.preButton);
        nxtButton = root.findViewById(R.id.nxtButton);


        //setting onClick listener to the buttons
        nxtButton.setOnClickListener(this);
        preButton.setOnClickListener(this);


        setMonthView();


        // Write a message to the database
        String UID  = mFirebaseAuth.getUid();
        //build a ref for user related data in real time DataBase using user id
        DatabaseReference myRef = database.getReference("users/"+UID);
        //getReference returns root - the path is users / all (for me )



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setMonthView() {
        monthYearTV.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext().getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }


    public void previousMonthAction() {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction() {
        CalendarUtils.selectedDate  = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
   }

    public void onItemClick(int position, LocalDate date) {
        if(date != null){
            CalendarUtils.selectedDate = date;
            setMonthView();
        }


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.nxtButton:
                nextMonthAction();
                break;
            case R.id.preButton:
                previousMonthAction();
                break;
            default:
                break;
        }
    }

}
