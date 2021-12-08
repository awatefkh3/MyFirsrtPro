package com.example.myfirsrtpro;

import java.util.Date;

public class Period {
    private Date firstDay;
    private Date lastDay;
    private int counter1;//duration
    private int counter2;//circle length

    //first period in the calendar
    public Period(Date firstDay) {
        this.firstDay = firstDay;
    }

    //all periods but the first
    public Period(Date firstDay, Date lastDay, int counter1, int counter2) {
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.counter1 = counter1;
        this.counter2 = counter2;
    }
    public Period(){

    }


    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public int getCounter1() {
        return counter1;
    }

    public void setCounter1(int counter1) {
        this.counter1 = counter1;
    }

    public int getCounter2() {
        return counter2;
    }

    public void setCounter2(int counter2) {
        this.counter2 = counter2;
    }
}
