package com.health.darynaosipenko.healthyschedule.status

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

import com.health.darynaosipenko.healthyschedule.R
import com.health.darynaosipenko.healthyschedule.create_schedule.CreateScheduleActivity
import com.health.darynaosipenko.healthyschedule.schedule.ScheduleActivity

class StatusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.status_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.status_next) {
            showScheduleActivity()
        }
        return super.onOptionsItemSelected(item)
    }


    fun showDialogNeedVisitDoctor(message: String) {}

    fun showScheduleActivity() {
        val intent = Intent(this, ScheduleActivity::class.java)
        startActivity(intent)
    }
}
