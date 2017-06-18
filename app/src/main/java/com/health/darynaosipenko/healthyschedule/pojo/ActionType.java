package com.health.darynaosipenko.healthyschedule.pojo;

import java.io.Serializable;

/**
 * Created by darynaosipenko on 5/27/17.
 */

public class ActionType implements Serializable {
    private ActivityType activityType;
    private int count;

    public enum ActivityType {
        SLEEP, EAT, SPORT, WATER, NONE
    }

    public ActionType(ActivityType activityType, int count) {
        this.activityType = activityType;
        this.count = count;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
