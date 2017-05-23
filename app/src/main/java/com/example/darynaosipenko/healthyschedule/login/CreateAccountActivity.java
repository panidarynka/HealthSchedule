package com.example.darynaosipenko.healthyschedule.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.darynaosipenko.healthyschedule.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_account);
    }
    public void onNextClicked(View view){
        startActivity(new Intent(this, DataInput.class));
    }
}
