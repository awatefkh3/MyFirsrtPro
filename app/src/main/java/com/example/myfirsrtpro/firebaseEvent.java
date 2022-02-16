package com.example.myfirsrtpro;

public class firebaseEvent {

    private MyTime time;
    private MyDate date;
    private String name;

    public firebaseEvent(MyTime time, MyDate date, String name) {
        this.time = time;
        this.date = date;
        this.name = name;
    }

    public firebaseEvent(){
        this.time = new MyTime();
        this.date = new MyDate();
    }



    public MyTime getTime() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
