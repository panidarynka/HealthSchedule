package com.health.darynaosipenko.healthyschedule.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.health.darynaosipenko.healthyschedule.R;
import com.health.darynaosipenko.healthyschedule.create_schedule.CreateScheduleActivity;
import com.health.darynaosipenko.healthyschedule.pojo.UserInfo;
import com.health.darynaosipenko.healthyschedule.status.StatusActivity;

public class DataInput extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    ImageView imageViewInfo;
    UserInfo.PhysicalActivity physicalActivity;
    private static final String[] paths = {"Too small", "Small", "Middle", "Big", "Too big"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input_first);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataInput.this,
                android.R.layout.simple_spinner_item, UserInfo.PhysicalActivity.getValuesName());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        imageViewInfo = (ImageView) findViewById(R.id.info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.data_input_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.data_input_next) {
            onNextItemClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showCreateScheduleActivity(UserInfo userInfo) {
        Intent intent = new Intent(this, CreateScheduleActivity.class);
        intent.putExtra("USER_INFO", userInfo);
        startActivity(intent);
    }

    public void onNextItemClicked() {
        EditText editTextAge = (EditText)findViewById(R.id.ageET);
        RadioButton radioButtonGender = (RadioButton) findViewById(R.id.radioMale);
        UserInfo.Gender userGender;
        if (radioButtonGender.isChecked()){
            userGender = UserInfo.Gender.MALE;
        } else {
            userGender = UserInfo.Gender.FAMALE;
        }
        EditText editTextWeight = (EditText)findViewById(R.id.weightET);
        EditText editTextWHeight = (EditText)findViewById(R.id.heightET);
        UserInfo userInfo = new UserInfo(Integer.parseInt(editTextAge.getText().toString()),
                userGender,
                physicalActivity,
                Integer.parseInt(editTextWeight.getText().toString()),
                Integer.parseInt(editTextWHeight.getText().toString()));
        showCreateScheduleActivity(userInfo);
    }

    public void showErrorInputDialog(String errorMessage) {
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        physicalActivity = UserInfo.PhysicalActivity.getEnumByString(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onInfoClicked(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Physical activity");
        builder.setMessage(R.string.activity_info);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
