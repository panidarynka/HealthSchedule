package com.example.darynaosipenko.healthyschedule.create_schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.darynaosipenko.healthyschedule.schedule.MainActivity;
import com.example.darynaosipenko.healthyschedule.R;
import com.example.darynaosipenko.healthyschedule.pojo.DayItem;
import com.example.darynaosipenko.healthyschedule.utils.DayItemsAdapter;
import com.example.darynaosipenko.healthyschedule.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CreateScheduleActivity extends AppCompatActivity {
    RecyclerItemClickListener recyclerItemClickListener;
    String TAG = CreateScheduleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.schedule);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<DayItem> dayItemList = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            dayItemList.add(new DayItem(String.valueOf(i), "17", "Sleep"));
        }
        Log.i(TAG, "onCreate: "+ dayItemList.size());
        DayItemsAdapter dayItemsAdapter = new DayItemsAdapter(dayItemList);
        recyclerView.setAdapter(dayItemsAdapter);
        if (recyclerItemClickListener != null) {
            recyclerView.removeOnItemTouchListener(recyclerItemClickListener);
        }
        recyclerItemClickListener = new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("TAG", "onItemClick: ");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.schedule_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.schedule_next){
            startActivity(new Intent(this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
