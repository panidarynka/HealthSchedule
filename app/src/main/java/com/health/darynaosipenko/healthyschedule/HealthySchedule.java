package com.health.darynaosipenko.healthyschedule;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by darynaosipenko on 5/30/17.
 */

public class HealthySchedule extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
