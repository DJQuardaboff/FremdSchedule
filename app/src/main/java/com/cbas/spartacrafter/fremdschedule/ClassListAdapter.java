/*
 * Created by smith1246 on 2/17/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public class ClassListAdapter implements ListAdapter {
    Schedule schedule;

    public ClassListAdapter(int scheduleType) {
        schedule = new Schedule(scheduleType);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return schedule.getClassPeriod(position).isActive();
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
        return schedule.length();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO finish
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        //TODO finish
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        //TODO finish
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return schedule.length() == 0;
    }
}
