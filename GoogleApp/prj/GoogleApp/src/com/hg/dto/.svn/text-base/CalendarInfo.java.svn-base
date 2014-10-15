package com.hg.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hg.util.DateUtil;

public class CalendarInfo {

    /** current time show */
    private String caption;
    private String previousMon;// format MMM
    private String nextMon;// format MMM
    private Calendar currentTime;

    /** current month's date info */
    private List<DateInfo> dates = new ArrayList<DateInfo>(0);

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPreviousMon() {
        return previousMon;
    }

    public void setPreviousMon(String previousMon) {
        this.previousMon = previousMon;
    }

    public String getNextMon() {
        return nextMon;
    }

    public void setNextMon(String nextMon) {
        this.nextMon = nextMon;
    }

    public List<DateInfo> getDates() {
        return dates;
    }

    public void setDates(List<DateInfo> dates) {
        this.dates = dates;
    }

    public Calendar getCurrentTime() {
        Calendar copy = DateUtil.getCurrentCal();
        copy.setTime(currentTime.getTime());
        return copy;
    }

    public void setCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "CalendarInfo [caption=" + caption + ", currentTime=" + currentTime + ", nextMon=" + nextMon
                + ", previousMon=" + previousMon + "]";
    }

}
