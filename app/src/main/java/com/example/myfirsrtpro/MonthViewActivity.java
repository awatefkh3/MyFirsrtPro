package com.example.myfirsrtpro;

import static com.example.myfirsrtpro.CalendarUtils.daysInMonthArray;
import static com.example.myfirsrtpro.CalendarUtils.monthYearFromDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MonthViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener, DialogInterface.OnClickListener{


    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cal_example);
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();

    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }



    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
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

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == dialogInterface.BUTTON_POSITIVE){
            super.onBackPressed();
            dialogInterface.cancel();
        }
        if(i == dialogInterface.BUTTON_POSITIVE){
            dialogInterface.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure ? this will log you out");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", this);
        builder.setNegativeButton("No",this);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    //inflates the design of the required menu on top of the activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.reminder_menu:
                Intent intent = new Intent(this, ReminderActivity.class);
                startActivity(intent);
            case R.id.alarm_menu:
                break;
            case R.id.about_menu:
                //Toast.makeText(MyCalExample.this,"About Us",Toast.LENGTH_LONG).show();//a toast is a message that appears on the screen and disappears
                Intent intent1 = new Intent(this,AboutActivity.class);
                startActivity(intent1);
                break;
            case R.id.logOut_menu:
                onBackPressed();
                break;
            case R.id.camera_menu:
                Intent intent2 = new Intent(this,ProfileActivity.class);
                startActivity(intent2);
                break;
            case R.id.gallery_menu:
                Intent intent3 = new Intent(this,ProfileActivity.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);

    }




    public void WeeklyAction(View view){
        startActivity(new Intent(this,WeekViewActivity.class));
    }


}