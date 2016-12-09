package com.wedevol.smartclass.models;

import java.util.Date;

/** Created by paolorossi on 12/8/16.*/
public class Schedule {
    private int weekday;
    private int startTime;
    private int endTime;
    //TODO warn becerrita that a true date needs to be implemented
    private Date date;

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Prints the schedule in a presentation format.
    * */
    public String printSchedule() {
        return startTime + " - " + endTime;
    }
}