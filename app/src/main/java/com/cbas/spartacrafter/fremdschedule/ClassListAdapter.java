/**
 * Created by smith1246 on 2/17/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ClassListAdapter implements ListAdapter {
    private LayoutInflater inflater = (LayoutInflater) Main.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    private Schedule schedule;

    public ClassListAdapter(int scheduleType) {
        schedule = new Schedule(scheduleType);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        switch(position % 2) {
            case 0:
                return schedule.getClassPeriod(position / 2).isActive();
            case 1:
                return true;
        }
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        //TODO finish
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        //TODO finish
    }

    @Override
    public int getCount() {
        return (schedule.size() * 2) - 1;
    }

    @Override
    public Object getItem(int position) {
        return schedule.getClassPeriod(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        switch(position % 2) {
            case 0:
                return schedule.getClassPeriod(position / 2).getView(inflater, parent);
            case 1:
                View v = new View(Main.getContext());
                v.setMinimumHeight(Main.getDP(2));
                v.setBackgroundColor(0x1f0f0f0f);
                return v;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return schedule.size() == 0;
    }
}
