package com.health.darynaosipenko.healthyschedule.pojo;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by darynaosipenko on 5/19/17.
 */

public class DayItem implements Serializable{

    private DateTime startTime;
    private DateTime endTime;
    private ActionType actionType;
    private boolean isDone;


    public DayItem(DateTime startTime, DateTime endTime, ActionType actionType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.actionType = actionType;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
