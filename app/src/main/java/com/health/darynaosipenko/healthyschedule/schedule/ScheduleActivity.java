package com.health.darynaosipenko.healthyschedule.schedule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.health.darynaosipenko.healthyschedule.R;
import com.health.darynaosipenko.healthyschedule.create_schedule.CreateScheduleActivity;
import com.health.darynaosipenko.healthyschedule.login.LoginActivity;
import com.health.darynaosipenko.healthyschedule.pojo.ActionType;
import com.health.darynaosipenko.healthyschedule.pojo.DayItem;
import com.health.darynaosipenko.healthyschedule.status.StatusActivity;
import com.health.darynaosipenko.healthyschedule.utils.CreateScheduleAdapter;
import com.health.darynaosipenko.healthyschedule.utils.RecyclerItemClickListener;
import com.health.darynaosipenko.healthyschedule.utils.ScheduleAdapter;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    List<DayItem> dayItemList;
    ScheduleAdapter adapter;
    RecyclerItemClickListener recyclerItemClickListener;
    List<DayItem> finalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getSerializableExtra("DAY_ITEMS") != null) {
            dayItemList = (List<DayItem>) getIntent().getSerializableExtra("DAY_ITEMS");
//            Log.i("TAG", "onCreate: " + dayItemList.get(1).getActionType().getActivityType().name());
            Log.i("TAG", "onCreate: " + dayItemList.size());
            finalList =  new ArrayList<DayItem>();
            Log.i("TAG", "onCreate: "+ finalList.size());
            for (int i = 0; i < dayItemList.size(); i++) {
                Log.i("TAG", "onCreate: " + i + " " + dayItemList.get(i).getActionType().getActivityType());
                if (dayItemList.get(i).getActionType().getActivityType() != ActionType.ActivityType.NONE) {
                    Log.i("TAG", "onCreate: remove" + i);
                    finalList.add(dayItemList.get(i));
                }
            }
            Log.i("TAG", "onCreate: " + dayItemList.size());
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.schedule_done);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (dayItemList != null) {
            adapter = new ScheduleAdapter(finalList);
            recyclerView.setAdapter(adapter);
        }
//        if (recyclerItemClickListener != null) {
//            recyclerView.removeOnItemTouchListener(recyclerItemClickListener);
//        }
//        recyclerItemClickListener = new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position, MotionEvent event, int adapterPosition) {
//                adapter.notifyDataSetChanged();
//
//            }
//        });
//        recyclerView.addOnItemTouchListener(recyclerItemClickListener);
    }

    public void onSetClicked(View view) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Thanks for checking");
        builder.setTitle("Live health!");
        builder.setPositiveButton("SET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.schedule_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_schedule) {
            showCreateScheduleActivity();
        }
        if (item.getItemId() == R.id.show_status) {
            showStatusActivity();
        }
        if (item.getItemId() == R.id.sign_out) {
            signOutClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showStatusActivity() {
        Intent intent = new Intent(this, StatusActivity.class);
        startActivity(intent);
    }

    public void showCreateScheduleActivity() {
        Intent intent = new Intent(this, CreateScheduleActivity.class);
        startActivity(intent);
    }

    public void showLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void signOutClicked() {
        FirebaseAuth.getInstance().signOut();
        // Google sign out
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(@NonNull Status status) {
//                        showLoginActivity();
//                    }
//                });
    }

}
