package com.health.darynaosipenko.healthyschedule.create_schedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.health.darynaosipenko.healthyschedule.pojo.ActionType;
import com.health.darynaosipenko.healthyschedule.pojo.UserInfo;
import com.health.darynaosipenko.healthyschedule.schedule.ScheduleActivity;
import com.health.darynaosipenko.healthyschedule.R;
import com.health.darynaosipenko.healthyschedule.pojo.DayItem;
import com.health.darynaosipenko.healthyschedule.utils.CreateScheduleAdapter;
import com.health.darynaosipenko.healthyschedule.utils.RecyclerItemClickListener;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreateScheduleActivity extends AppCompatActivity {
    RecyclerItemClickListener recyclerItemClickListener;
    String TAG = CreateScheduleActivity.class.getSimpleName();
    CreateSchedulePresenter presenter = new CreateSchedulePresenter();
    CreateScheduleAdapter createScheduleAdapter;
    List<DayItem> dayItemList;
    private String timeFormat = "HH:mm";
    TextView sleepCount, eatCount, waterCount, activityCount;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        userInfo = (UserInfo) getIntent().getSerializableExtra("USER_INFO");
        Log.i(TAG, "onCreate: "+ userInfo.getGender());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.schedule);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dayItemList = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (i == 23){
                dayItemList.add(new DayItem(new DateTime().withHourOfDay(i).withMinuteOfHour(0),
                        new DateTime().withHourOfDay(0).withMinuteOfHour(0),
                        new ActionType(ActionType.ActivityType.NONE, 20)));
            } else {
                dayItemList.add(new DayItem(new DateTime().withHourOfDay(i).withMinuteOfHour(0),
                        new DateTime().withHourOfDay(i+1).withMinuteOfHour(0),
                        new ActionType(ActionType.ActivityType.NONE, 20)));
            }

        }
        Log.i(TAG, "onCreate: " + dayItemList.size());
        createScheduleAdapter = new CreateScheduleAdapter(dayItemList);
        recyclerView.setAdapter(createScheduleAdapter);
        if (recyclerItemClickListener != null) {
            recyclerView.removeOnItemTouchListener(recyclerItemClickListener);
        }
        recyclerItemClickListener = new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, MotionEvent event, int adapterPosition) {
                Log.i(TAG, "onItemClick: " + position + " " + adapterPosition);
                if (presenter.getCurrentActivity() == null) {
                    Toast.makeText(CreateScheduleActivity.this, "Please choose type of day action at first", Toast.LENGTH_SHORT).show();
                } else {
//                    if (presenter.getCurrentActivity() == ActionType.ActivityType.EAT) {
                    showSetDetailsDialog(dayItemList.get(position));
//                    }
                    dayItemList.get(position).getActionType().setActivityType(presenter.getCurrentActivity());
                    createScheduleAdapter.notifyDataSetChanged();
                }

            }
        });
        recyclerView.addOnItemTouchListener(recyclerItemClickListener);
        invalidateViews();
    }

    private void invalidateViews() {
        sleepCount = (TextView) findViewById(R.id.sleep_count);
        sleepCount.setText(presenter.getRecommendedSleep(userInfo)+ " hours");
        eatCount = (TextView) findViewById(R.id.eat_count);
        eatCount.setText(presenter.getRecommendedEat(userInfo) + " kcal");
        waterCount = (TextView) findViewById(R.id.water_count);
        waterCount.setText(String.format("%(.3f ", presenter.getRecommendedWater(userInfo)) + " L");
        activityCount = (TextView) findViewById(R.id.activity_count);
        activityCount.setText("1 hour");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_schedule_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.schedule_next) {
            onNextClicked(null);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showScheduleActivity() {
        Intent intent = new Intent(this, ScheduleActivity.class);
        intent.putExtra("DAY_ITEMS", (Serializable) dayItemList);
        startActivity(intent);
    }

    public void onNextClicked(View view) {
        showScheduleActivity();
    }

    public void onActionClicked(View view) {
        Log.i(TAG, "onActionClicked: " + view.getId());
        presenter.setActiveAction(view.getId());
    }

    public void showErrorDialog(String error) {

    }

    public DayItem showSetDetailsDialog(final DayItem dayItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set details");
//        // Set up the input
//        final EditText input = new EditText(this);
//        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//        input.setInputType(InputType.TYPE_CLASS_NUMBER);
//        builder.setView(input);
//
//        // Set up the buttons
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dayItem.getActionType().setCount(Integer.parseInt(input.getText().toString()));
//                createScheduleAdapter.notifyDataSetChanged();
//            }
//        });


        // Get the layout inflater
        LayoutInflater inflater = (this).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout
        builder.setView(dialogView);
        final EditText input = (EditText) dialogView.findViewById(R.id.count);
        final EditText inputStartTime = (EditText) dialogView.findViewById(R.id.time_start);
        final EditText inputEndTime = (EditText) dialogView.findViewById(R.id.time_end);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DateTimeFormatter timeFormatter = DateTimeFormat.forPattern(timeFormat);
                try {
                    DateTime startTime = DateTime.parse(inputStartTime.getText().toString(), timeFormatter);
                    DateTime endTime = DateTime.parse(inputEndTime.getText().toString(), timeFormatter);
                    dayItem.getActionType().setCount(Integer.parseInt(input.getText().toString()));
                    dayItem.setStartTime(startTime);
                    dayItem.setEndTime(endTime);
                    createScheduleAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.i(TAG, "onClick: " + e.getMessage());
                }


            }
        });
        builder.create();
        builder.show();


        return dayItem;

    }
}
