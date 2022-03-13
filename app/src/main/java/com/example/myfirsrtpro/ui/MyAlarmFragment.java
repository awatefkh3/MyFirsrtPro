package com.example.myfirsrtpro.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirsrtpro.MyAlarm;
import com.example.myfirsrtpro.NotificationReceiver;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.databinding.FragmentAlarmBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

public class MyAlarmFragment extends Fragment {

    private FragmentAlarmBinding binding;
    private EditText selected_time;
    private Button setAlarmBtn,cancelAlarmBtn;

    //gets instance of authentication project in FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time DataBase in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentAlarmBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        selected_time = root.findViewById(R.id.selected_time);
        setAlarmBtn = root.findViewById(R.id.setAlarmBtn);
        cancelAlarmBtn = root.findViewById(R.id.cancelAlarmBtn);



        setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = String.valueOf(LocalDate.now());

                String time = String.valueOf(selected_time.getText());
                MyAlarm alarm = new MyAlarm(time,date,"");

                String UID = mFirebaseAuth.getUid();
                //build a ref for user related data in real time DataBase using user id
                DatabaseReference myRef = database.getReference("alarms/" + UID);
                String key = myRef.push().getKey();
                myRef = database.getReference("alarms/" + UID + "/" + key);
                alarm.setKey(key);

                myRef.setValue(alarm);

                Toast.makeText(getContext(), "Alarm was created", Toast.LENGTH_SHORT).show();


            }
        });

        /*cancelAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UID = mFirebaseAuth.getUid();
                //build a ref for user related data in real time DataBase using user id
                DatabaseReference myRef = database.getReference("alarms/" + UID);

                String date = String.valueOf(LocalDate.now());

                String time = String.valueOf(selected_time.getText());

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Alarm alarm = dataSnapshot.getValue(Alarm.class);
                            if(String.valueOf(alarm.getDate()).equals(date) && String.valueOf(alarm.getTime()).equals(time)){
                                alarm.setOnOff(false);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });*/

      /*  //pending intent waiting for the right time
        Intent notifyIntent = new Intent(getContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (getContext(), NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);
*/



        return root;

    }

}
