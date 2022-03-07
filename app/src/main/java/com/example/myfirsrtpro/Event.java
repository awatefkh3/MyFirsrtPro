package com.example.myfirsrtpro;

import android.text.format.Time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

//public class Event {

    /*private String name;
    //private LocalDate date;
    //private LocalTime time;
    public static ArrayList<Event> eventList = new ArrayList<>();


    //private Date date1;
    //private Time time1;


    *//*public Event(LocalTime time){
        this.time = LocalTime.now();
    }


    public Event(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }
*//*
    public static ArrayList<Event> eventsForDate(LocalDate date){
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventList){
            if(event.getDate().equals(date))
                events.add(event);
        }

        return events;
    }

    *//*public static ArrayList<Event> eventsForDateAndTime(LocalDate date,LocalTime time){
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventList){
            int eventHour = event.time.getHour();
            int cellHour  = time.getHour();
            if(event.getDate().equals(date) && eventHour==cellHour)
                events.add(event);
        }

        return events;
    }*//*

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    *//*public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }*//*


}
*/
//todo delete this