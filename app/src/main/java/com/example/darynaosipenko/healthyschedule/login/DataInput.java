package com.example.darynaosipenko.healthyschedule.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.darynaosipenko.healthyschedule.R;
import com.example.darynaosipenko.healthyschedule.schedule.MainActivity;
import com.example.darynaosipenko.healthyschedule.status.StatusActivity;

public class DataInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input_first);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.data_input_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.data_input_next){
            Intent intent = new Intent(this, StatusActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
