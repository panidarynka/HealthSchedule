package com.example.darynaosipenko.healthyschedule.pojo;

/**
 * Created by darynaosipenko on 5/19/17.
 */

public class DayItem {
    String startTime;
    String endTime;
    String activityType;

    public DayItem(String startTime, String endTime, String activityType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
