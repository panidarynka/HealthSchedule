package com.example.darynaosipenko.healthyschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DataInputFirst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input_first);
    }
    public void onNextClick(View view){
        Intent intent = new Intent(this, StatusActivity.class);
        startActivity(intent);
    }

}
