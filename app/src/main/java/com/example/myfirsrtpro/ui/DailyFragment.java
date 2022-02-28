package com.example.myfirsrtpro.ui;

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

import com.example.myfirsrtpro.CalendarUtils;
import com.example.myfirsrtpro.Event;
import com.example.myfirsrtpro.EventEditActivity2;
import com.example.myfirsrtpro.HourAdapter;
import com.example.myfirsrtpro.HourEvent;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.databinding.FragmentDailyBinding;
import com.example.myfirsrtpro.databinding.FragmentWeeklyBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DailyFragment extends Fragment implements View.OnClickListener {

    private FragmentDailyBinding binding;
    private Button preDay,nxtDay,newEvent;

    private TextView monthDayText;
    private TextView dayOfWeekTV;
    private ListView hourListView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        CalendarUtils.selectedDate = LocalDate.now();


        binding = FragmentDailyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        monthDayText  = root.findViewById(R.id.monthDayText);
        dayOfWeekTV = root.findViewById(R.id.dayOfWeekTV);
        hourListView = root.findViewById(R.id.hourListView);



        preDay = root.findViewById(R.id.preDay);
        nxtDay = root.findViewById(R.id.nxtDay);
        newEvent = root.findViewById(R.id.newEvent);


        //setting onClock listener to the buttons
        preDay.setOnClickListener(this);
        nxtDay.setOnClickListener(this);
        newEvent.setOnClickListener(this);

        setDayView();


        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        setDayView();
    }

    private void setDayView() {
        monthDayText.setText(CalendarUtils.monthDayFromDate(CalendarUtils.selectedDate));
        String dayOfWeek = CalendarUtils.selectedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        dayOfWeekTV.setText(dayOfWeek);
        setHourAdapter();

    }

    private void setHourAdapter() {
        HourAdapter hourAdapter = new HourAdapter(getContext().getApplicationContext(),hourEventList());
        hourListView.setAdapter(hourAdapter);
    }

    private List<HourEvent> hourEventList() {
        ArrayList<HourEvent> list = new ArrayList<>();

        for(int hour = 0; hour<24; hour++){
            LocalTime time = LocalTime.of(hour,0);
            ArrayList<Event> events =Event.eventsForDateAndTime(CalendarUtils.selectedDate,time);
            HourEvent hourEvent = new HourEvent(time,events);
            list.add(hourEvent);

        }
        return list;
    }

    //to add event in another fragment
    public void addEvent(HourEvent event,ArrayList<HourEvent> list)
    {
        list.add(event);
    }

    public void previousDayAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusDays(1);
        setDayView();
    }

    public void nextDayAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusDays(1);
        setDayView();
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this.getActivity(), EventEditActivity2.class));
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.nxtDay:
                nextDayAction(nxtDay);
                break;
            case R.id.preDay:
                previousDayAction(preDay);
                break;
            case R.id.newEvent:
                newEventAction(newEvent);
                break;
            default:
                break;

        }
    }
}
