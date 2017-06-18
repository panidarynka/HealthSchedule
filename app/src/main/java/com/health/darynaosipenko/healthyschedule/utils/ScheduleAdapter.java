package com.health.darynaosipenko.healthyschedule.utils;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.health.darynaosipenko.healthyschedule.R;
import com.health.darynaosipenko.healthyschedule.pojo.ActionType;
import com.health.darynaosipenko.healthyschedule.pojo.DayItem;

import java.util.List;

/**
 * Created by darynaosipenko on 5/28/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<DayItem> dayItemList;

    public ScheduleAdapter(List<DayItem> dayItemList) {
        this.dayItemList = dayItemList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_item_schedule, parent, false);
        viewHolder = new ScheduleAdapter.ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ScheduleAdapter.ItemViewHolder itemViewHolder = (ScheduleAdapter.ItemViewHolder) holder;
        if (dayItemList.get(position).getActionType().getActivityType() == ActionType.ActivityType.NONE) {
            itemViewHolder.action.setText(" ");

        } else {
            itemViewHolder.action.setText(dayItemList.get(position).getActionType().getActivityType().name());
        }
        if (dayItemList.get(position).getActionType().getActivityType() == ActionType.ActivityType.EAT) {
            itemViewHolder.count.setVisibility(View.VISIBLE);
        } else {
            itemViewHolder.count.setVisibility(View.INVISIBLE);
        }

        itemViewHolder.timeStart.setText(String.valueOf(dayItemList.get(position).getStartTime().getHourOfDay()
                + ":" + dayItemList.get(position).getStartTime().getMinuteOfHour()));

        itemViewHolder.timeEnd.setText(String.valueOf(dayItemList.get(position).getEndTime().getHourOfDay()
                + ":" + dayItemList.get(position).getEndTime().getMinuteOfHour()));
        itemViewHolder.count.setText(dayItemList.get(position).getActionType().getCount() + " kcal");
        if (dayItemList.get(position).isDone()) {
            itemViewHolder.row_linearlayout.setBackgroundResource(R.color.gray);
            itemViewHolder.checkBox.setChecked(true);
        } else {
            itemViewHolder.checkBox.setChecked(false);
            switch (dayItemList.get(position).getActionType().getActivityType()) {
                case NONE:
                    itemViewHolder.row_linearlayout.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case SLEEP:
                    itemViewHolder.row_linearlayout.setBackgroundResource(R.color.sleep);
                    break;
                case EAT:
                    itemViewHolder.row_linearlayout.setBackgroundResource(R.color.eat);
                    break;
                case WATER:
                    itemViewHolder.row_linearlayout.setBackgroundResource(R.color.water);
                    break;
                case SPORT:
                    itemViewHolder.row_linearlayout.setBackgroundResource(R.color.sport);
                    break;
            }
        }

        itemViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (itemViewHolder.checkBox.isChecked()) {
                    itemViewHolder.row_linearlayout.setBackgroundResource(R.color.gray);
                    dayItemList.get(position).setDone(true);
                }
            }
        });

    }

    void setItemBackground(LinearLayout linearLayout) {

    }

    @Override
    public int getItemCount() {
        return dayItemList.size();
    }

    /**
     * Provide a reference to the type of views for Record
     */
    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView timeStart;
        TextView timeEnd;
        TextView action;
        TextView count;
        CheckBox checkBox;
        LinearLayout row_linearlayout;

        ItemViewHolder(View itemView) {
            super(itemView);
            timeStart = (TextView) itemView.findViewById(R.id.time_start);
            timeEnd = (TextView) itemView.findViewById(R.id.time_end);
            action = (TextView) itemView.findViewById(R.id.day_action);
            row_linearlayout = (LinearLayout) itemView.findViewById(R.id.row_linrLayout);
            count = (TextView) itemView.findViewById(R.id.count);
            checkBox = (CheckBox) itemView.findViewById(R.id.check_done);
        }
    }
}
