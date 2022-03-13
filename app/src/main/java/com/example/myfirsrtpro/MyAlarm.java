package com.example.myfirsrtpro;

import java.sql.Time;
import java.util.Date;

public class MyAlarm {
    private String date;
    private String time;
    private String desc;
    private boolean onOff;
    private String key;


    public MyAlarm(String date, String time, String desc) {
        this.date = date;
        this.time = time;
        this.desc = desc;
        this.onOff = true;
    }

    public MyAlarm(){

    }

    public boolean isOnOff(){
        return this.onOff;
    }

    public void setOnOff(boolean onOff){
        this.onOff = onOff;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
