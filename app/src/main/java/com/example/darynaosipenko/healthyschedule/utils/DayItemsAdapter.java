package com.example.darynaosipenko.healthyschedule.utils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.darynaosipenko.healthyschedule.R;
import com.example.darynaosipenko.healthyschedule.pojo.DayItem;

import java.util.List;

/**
 * Created by darynaosipenko on 5/19/17.
 */

public class DayItemsAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<DayItem> dayItemList;

        public DayItemsAdapter(List<DayItem> dayItemList) {
            this.dayItemList = dayItemList;

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            RecyclerView.ViewHolder viewHolder;
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.day_item, parent, false);
                viewHolder = new ItemViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
                final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

                itemViewHolder.action.setText(dayItemList.get(position).getActivityType());
                itemViewHolder.time.setText(dayItemList.get(position).getStartTime());

        }

        @Override
        public int getItemCount() {
            return dayItemList.size();
        }

        /**
         * Provide a reference to the type of views for Record
         */
        private static class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView time;
            TextView action;

            ItemViewHolder(View itemView) {
                super(itemView);
                time = (TextView) itemView.findViewById(R.id.time);
                action = (TextView) itemView.findViewById(R.id.day_action);
            }
        }
}
