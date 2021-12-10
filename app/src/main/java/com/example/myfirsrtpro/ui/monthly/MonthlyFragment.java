package com.example.myfirsrtpro.ui.monthly;


import static com.example.myfirsrtpro.CalendarUtils.daysInMonthArray;
import static com.example.myfirsrtpro.CalendarUtils.monthYearFromDate;

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
import com.example.myfirsrtpro.Item;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.WeekViewActivity;
import com.example.myfirsrtpro.databinding.FragmentMonthlyBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;

public class MonthlyFragment extends Fragment implements CalendarAdapter.OnItemListener{
    private FragmentMonthlyBinding binding;
    //gets instance of authentication project in FB console
    private FirebaseAuth mFirebaseAuth  = FirebaseAuth.getInstance();
    //gets the root of the real time DataBase in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");
    private TextView monthYearTV;
    private RecyclerView calendarRecyclerView;
    private Button weeklyButton,preButton,nxtButton;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        CalendarUtils.selectedDate = LocalDate.now();



        binding = FragmentMonthlyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        calendarRecyclerView = root.findViewById(R.id.calendarRecyclerView);
        monthYearTV = root.findViewById(R.id.monthYearTV);
        preButton = root.findViewById(R.id.preButton);
        nxtButton = root.findViewById(R.id.nxtButton);


        setMonthView();

//        final TextView textView = binding.textAbout;
//        aboutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        // Write a message to the database
        String UID  = mFirebaseAuth.getUid();
        //build a ref for user related data in real time DataBase using user id
        DatabaseReference myRef = database.getReference("users/"+UID);
        //getReference returns root - the path is users / all (for me )

        //todo this
        //adds an item to the FB under the referenced specified
        Item item1 = new Item();
        myRef.push().setValue(item1); //put the object

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //for each,children are the objects
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Item item1 = dataSnapshot.getValue(Item.class);
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


    public void previousMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        CalendarUtils.selectedDate  = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
   }

    public void onItemClick(int position, LocalDate date) {
        if(date != null){
            CalendarUtils.selectedDate = date;
            setMonthView();
        }


    }
    public void WeeklyAction(View view){
        startActivity(new Intent(this.getActivity(), WeekViewActivity.class));
    }



}
