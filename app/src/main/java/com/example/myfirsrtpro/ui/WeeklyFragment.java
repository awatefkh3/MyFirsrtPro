package com.example.myfirsrtpro.ui;

import static com.example.myfirsrtpro.CalendarUtils.daysInWeekArray;
import static com.example.myfirsrtpro.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirsrtpro.CalendarAdapter;
import com.example.myfirsrtpro.CalendarUtils;
import com.example.myfirsrtpro.DailyCalendarActivity;
import com.example.myfirsrtpro.Event;
import com.example.myfirsrtpro.EventAdapter;
import com.example.myfirsrtpro.R;

import com.example.myfirsrtpro.databinding.FragmentWeeklyBinding;


import java.time.LocalDate;
import java.util.ArrayList;

public class WeeklyFragment extends Fragment implements CalendarAdapter.OnItemListener, View.OnClickListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;
    private FragmentWeeklyBinding binding;
    private Button preButtonWeek,nxtButtonWeek;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        CalendarUtils.selectedDate = LocalDate.now();


        binding = FragmentWeeklyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        calendarRecyclerView = root.findViewById(R.id.calendarRecyclerView);
        monthYearText = root.findViewById(R.id.monthYearTV);
        eventListView = root.findViewById(R.id.eventListView);

        preButtonWeek = root.findViewById(R.id.preButtonWeek);
        nxtButtonWeek = root.findViewById(R.id.nxtButtonWeek);


        //setting onClock listener to the buttons
        nxtButtonWeek.setOnClickListener(this);
        preButtonWeek.setOnClickListener(this);

        setWeekView();


        return root;

    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext().getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter() {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getContext().getApplicationContext(), dailyEvents);

        eventListView.setAdapter(eventAdapter);
    }


    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

   /* public void dailyAction(View view){
        startActivity(new Intent(this.getActivity(), DailyCalendarActivity.class));
    }
*/
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.nxtButtonWeek:
                nextWeekAction(nxtButtonWeek);
                break;
            case R.id.preButtonWeek:
                previousWeekAction(preButtonWeek);
                break;
            default:
                break;
        }
    }


}
