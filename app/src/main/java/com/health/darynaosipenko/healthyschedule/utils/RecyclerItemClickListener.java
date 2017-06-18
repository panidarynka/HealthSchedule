package com.health.darynaosipenko.healthyschedule.utils;

/**
 * Created by darynaosipenko on 5/19/17.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener itemClickListener;
    private GestureDetector gestureDetector;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, MotionEvent event, int adapterPosition);
    }

    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        itemClickListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && itemClickListener != null && gestureDetector.onTouchEvent(e)) {
            itemClickListener.onItemClick(childView, rv.getChildAdapterPosition(childView), e, rv.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}