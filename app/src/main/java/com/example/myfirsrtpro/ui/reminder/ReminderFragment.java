package com.example.myfirsrtpro.ui.reminder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfirsrtpro.Item;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.Reminder;
import com.example.myfirsrtpro.ReminderActivity;
import com.example.myfirsrtpro.databinding.FragmentMonthlyBinding;
import com.example.myfirsrtpro.databinding.FragmentReminderBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ReminderFragment extends Fragment implements DialogInterface.OnClickListener {


    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    //gets instance of authentication project in FB console
    private FirebaseAuth mFirebaseAuth  = FirebaseAuth.getInstance();
    //gets the root of the real time DataBase in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");

    private ReminderViewModel reminderViewModel;
    private FragmentReminderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reminderViewModel =
                new ViewModelProvider(this).get(ReminderViewModel.class);

        binding = FragmentReminderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dateButton = root.findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker(view);
            }
        });
        initDatePicker();
        // Write a message to the database
        String UID  = mFirebaseAuth.getUid();
        //build a ref for user related data in real time DataBase using user id
        DatabaseReference myRef = database.getReference("users/"+UID);
        //getReference returns root - the path is users / all (for me )

        //todo this
        //adds an item to the FB under the referenced specified
        Reminder reminder1 = new Reminder();
        myRef.push().setValue(reminder1); //put the object

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //for each,children are the objects
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Reminder reminder1 = dataSnapshot.getValue(Reminder.class);
                    //list.add(item1);--> add to the arrayList
                    //myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1 ;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1 ;
                String date = makeDateString(day,month,year);
                dateButton.setText(date);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        View root = binding.getRoot();
        datePickerDialog = new DatePickerDialog(root.getContext(),style,dateSetListener,year,month,day);

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) +" "+day+" "+year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";
        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == dialogInterface.BUTTON_POSITIVE){
            saveReminder(dialogInterface);
        }
        if(i == dialogInterface.BUTTON_NEGATIVE){
            dialogInterface.cancel();
        }
    }
    public void saveReminder(DialogInterface dialogInterface){

    }




}
