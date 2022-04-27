package com.example.myfirsrtpro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirsrtpro.CalendarUtils;
import com.example.myfirsrtpro.EventEditActivity;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.databinding.FragmentDailyBinding;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DailyFragment extends Fragment implements View.OnClickListener {

    private FragmentDailyBinding binding;
    private Button preDay,nxtDay,newEvent;

    private TextView monthDayText;
    private TextView dayOfWeekTV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        CalendarUtils.selectedDate = LocalDate.now();


        binding = FragmentDailyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        monthDayText  = root.findViewById(R.id.monthDayText);
        dayOfWeekTV = root.findViewById(R.id.dayOfWeekTV);



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


    }



    public void previousDayAction() {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusDays(1);
        setDayView();
    }

    public void nextDayAction() {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusDays(1);
        setDayView();
    }

    public void newEventAction() {
        startActivity(new Intent(this.getActivity(), EventEditActivity.class));
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.nxtDay:
                nextDayAction();
                break;
            case R.id.preDay:
                previousDayAction();
                break;
            case R.id.newEvent:
                newEventAction();
                break;
            default:
                break;

        }
    }
}
