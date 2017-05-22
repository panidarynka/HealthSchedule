package com.example.darynaosipenko.healthyschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
    }
    public void onNextClick(View view){
        Intent intent = new Intent(this, CreateScheduleActivity.class);
        startActivity(intent);
    }
}
