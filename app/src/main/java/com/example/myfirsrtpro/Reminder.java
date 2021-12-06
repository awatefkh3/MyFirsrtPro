package com.example.myfirsrtpro;

import java.sql.Time;
import java.util.Date;

public class Reminder {
    private Date date ;
    private Time time;
    private String desc;

    public Reminder(Date date, Time time, String desc) {
        this.date = date;
        this.time = time;
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
