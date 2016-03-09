/**
 * Created by smith1246 on 2/17/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater mInflater = (LayoutInflater) Main.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.class_list_item, null);

            TextView title = (TextView) convertView.findViewById(R.id.item_title);
            TextView subtitle = (TextView) convertView.findViewById(R.id.item_subtitle);
            ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.item_progress);
        }

        ///#use    return convertView;
        return convertView;
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
        return schedule.length() == 0;
    }
}
