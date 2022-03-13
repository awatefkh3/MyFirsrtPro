package com.example.myfirsrtpro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirsrtpro.CalendarUtils;
import com.example.myfirsrtpro.EventEditActivity2;
import com.example.myfirsrtpro.FireBaseEvent;
import com.example.myfirsrtpro.FireBaseEventAdapter;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.databinding.FragmentDailyBinding;
import com.example.myfirsrtpro.databinding.FragmentEventsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class EventsFragment extends Fragment {

    private FragmentEventsBinding binding;

    private ListView eventsListView;

    private ArrayList<FireBaseEvent> eventlist;

    private FireBaseEventAdapter myAdapter;

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        CalendarUtils.selectedDate = LocalDate.now();


        binding = FragmentEventsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        eventsListView = root.findViewById(R.id.eventsListView);


        //connect listView with adapter+arrayList
        eventlist = new ArrayList<>();
        myAdapter = new FireBaseEventAdapter(getContext().getApplicationContext(), R.layout.event_cell, eventlist);

        String UID = mFirebaseAuth.getUid();
        //build a ref for user related data in real time DataBase using user id
        DatabaseReference myRef = database.getReference("events/" + UID);

        //connect adapter with view
        eventsListView.setAdapter(myAdapter);
        eventsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                myRef.child(eventlist.get(i).getKey()).removeValue();
                eventlist.remove(i);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });

        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        setEventAdapter();
    }


    private void setEventAdapter() {
        //gets instance of authentication project in FB console
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        //gets the root of the real time DataBase in the FB console
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");
        String UID = mFirebaseAuth.getUid();
        DatabaseReference myRef = database.getReference("events/" + UID);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myAdapter.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FireBaseEvent fireBaseEvent = dataSnapshot.getValue(FireBaseEvent.class);
                    eventlist.add(fireBaseEvent);
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //for each,children are the objects
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/
    }
}











