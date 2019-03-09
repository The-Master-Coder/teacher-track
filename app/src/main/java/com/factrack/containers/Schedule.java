package com.factrack.containers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    Map<String, List<Slot>> schedules = new HashMap<String, List<Slot>>();

    public Schedule() {

    }
    public Schedule(Map<String, List<Slot>> schedules) {
        this.schedules = schedules;
    }

}

