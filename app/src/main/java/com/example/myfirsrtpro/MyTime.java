package com.example.myfirsrtpro;

public class MyTime {

    private String hour;
    private String minute;
    private String second;

    public MyTime(){

    }
    public MyTime(String hour, String minute, String second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
