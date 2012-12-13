package com.hg.dto;

import java.util.Date;

public class DateInfo {

    private int date;
    private int day;
    private boolean isToday;
    private Date dateTime;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    /** start with Sunday(Min 1) */
    public int getDay() {
        return day;
    }

    /** start with Sunday(Min 1) */
    public void setDay(int day) {
        this.day = day;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean isToday) {
        this.isToday = isToday;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "DateInfo [date=" + date + ", dateTime=" + dateTime + ", day=" + day + ", isToday=" + isToday + "]";
    }

}
