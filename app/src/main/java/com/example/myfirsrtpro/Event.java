package com.example.myfirsrtpro;


public class Event {


    private String time;
    private String date;
    private String name;
    private String key;
    private boolean done;


    public Event(String time, String date, String name) {
        this.time = time;
        this.date = date;
        this.name = name;
        this.done = false;
    }

    public Event(){

    }

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


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
