package com.example.darynaosipenko.healthyschedule.status;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.darynaosipenko.healthyschedule.R;
import com.example.darynaosipenko.healthyschedule.create_schedule.CreateScheduleActivity;
import com.example.darynaosipenko.healthyschedule.schedule.MainActivity;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.status_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.status_next){
            Intent intent = new Intent(this, CreateScheduleActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
