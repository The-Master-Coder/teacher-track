package com.factrack.containers;

import java.sql.Time;

public class Slot {
    public String roomNo ;
    public String building;
    public int startHour;
    public int startMinute;
    public int endHour;
    public int endMinute;

    public  Slot () {

    }

    public Slot(String roomNo, String building, int startHour, int startMinute, int endHour, int endMinute) {
        this.roomNo = roomNo;
        this.building = building;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
    }
}
