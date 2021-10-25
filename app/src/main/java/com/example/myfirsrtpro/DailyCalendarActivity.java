package com.example.myfirsrtpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.format.TextStyle;

public class DailyCalendarActivity extends AppCompatActivity {

    private TextView monthDayText;
    private TextView dayOfWeekTV;
    private ListView hourListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_calendar);
        initWidgets();
    }

    private void initWidgets() {
        monthDayText  = findViewById(R.id.monthYearTV);
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV);
        hourListView = findViewById(R.id.hourListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDayView();
    }

    private void setDayView() {
        monthDayText.setText(CalendarUtils.monthDayFromDate(selectedDate));
        String dayOfWeek = selectedDate.getDateOfWeek().getDisplayName(TextStyle.FULL,locale.getDefault());
        dayOfWeekTV.setText(dayOfWeek);
    }

    public void previousDayAction(View view) {

    }

    public void nextDayAction(View view) {
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this,EventEditActivity.class));
    }
}