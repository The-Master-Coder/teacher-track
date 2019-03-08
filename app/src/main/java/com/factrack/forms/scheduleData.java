package com.factrack.forms;

public class scheduleData {
    public String day;
    public String building;
    public String roomNo;
    public int startHour, startMinute, endHour, endMinute;

    public scheduleData(){

    }

    public scheduleData(String day, String building, String roomNo,int startHour,int startMinute,
                        int endHour, int endMinute) {
        this.day = day;
        this.building = building;
        this.roomNo = roomNo;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

}
