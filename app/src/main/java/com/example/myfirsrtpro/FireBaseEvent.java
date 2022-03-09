package com.example.myfirsrtpro;

import android.icu.util.IndianCalendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FireBaseEvent {

    //private MyTime time;
    //private MyDate date;
    private String time;
    private String date;
    private String name;


  /*  public FireBaseEvent(MyTime time, MyDate date, String name) {
        this.time = new MyTime(time.getHour(),time.getMinute(),time.getSecond());
        this.date = new MyDate(date.getDay(),date.getMonth(),date.getYear());
        this.name = name;
    }*/

    public FireBaseEvent(String time, String date, String name) {
        this.time = time;
        this.date = date;
        this.name = name;
    }


    public FireBaseEvent(){

    }

   /* public MyTime getTime() {
        return time;
    }

    public void setTime(MyTime time) {
        this.time = time;
    }

    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate date) {
        this.date = date;
    }
*/
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getTime(){
        return this.time;
        }

    public void setTime(String time){
        this.time = time;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }


}
