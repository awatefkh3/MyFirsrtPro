package com.example.myfirsrtpro.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirsrtpro.CalendarUtils;
import com.example.myfirsrtpro.Event;
import com.example.myfirsrtpro.EventAdapter;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.databinding.FragmentEventsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventsFragment extends Fragment {


    private FragmentEventsBinding binding;

    private ListView eventsListView;

    private ArrayList<Event> eventlist;

    private EventAdapter myAdapter;

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
        myAdapter = new EventAdapter(getContext().getApplicationContext(), R.layout.event_cell, eventlist);

        String UID = mFirebaseAuth.getUid();
        //build a ref for user related data in real time DataBase using user id
        DatabaseReference myRef = database.getReference("events/" + UID);

        //connect adapter with view
        eventsListView.setAdapter(myAdapter);


        // long click on event listener
        eventsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //responding
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                myRef.child(eventlist.get(i).getKey()).removeValue(); //removing the event from the firebase
                myAdapter.notifyDataSetChanged(); //notifying and updating the adapter about the change
                return false;
            }
        });

        // click on event listener
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //responding
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Event event2 = (Event) eventsListView.getItemAtPosition(i); //getting the event from the list
                //if the event is not done yet --> change to done
                if(!event2.isDone()){
                    myRef.child(eventlist.get(i).getKey()).child("done").setValue(true);
                }
                //if the event is done --> change to not done
                else if(event2.isDone()){
                    myRef.child(eventlist.get(i).getKey()).child("done").setValue(false);
                }
                myAdapter.notifyDataSetChanged();//notifying and updating the adapter about the change
            }
        });
//

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
                myAdapter.clear(); //clearing the adapter to avoid mistaken results
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Event fireBaseEvent = dataSnapshot.getValue(Event.class);
                    eventlist.add(fireBaseEvent); //adds the event to the events list that reflects the events in the firebase
                    myAdapter.notifyDataSetChanged(); //notifying the adapter about the change --> updating it
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }



}











