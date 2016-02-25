/*
 * Created by smith1246 on 2/17/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class ClassListAdapter implements ListAdapter {
    Schedule schedule;

    public ClassListAdapter(int type) {
        for () {

        }
        try {

        } catch (IOException e) {
            Toast.makeText(Main.getContext(), "Could not connect to http://fhs.d211.org/.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            throw new RuntimeException("Could not connect to http://fhs.d211.org/: " + e.getMessage());
        }

        try {
            classPeriods = Schedule.getClassPeriodsFromFile(new File(Main.getContext().getFilesDir(), "classPeriods.txt"));
        } catch (IOException e) {
            Toast.makeText(Main.getContext(), "Could not open class period name file.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            throw new RuntimeException("Could not open class period name file: " + e.getMessage());
        }
    }



    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return classPeriods[position].isInSession();
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
        return classPeriods.length;
    }

    @Override
    public Object getItem(int position) {
        return classPeriods[position];
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
        return classPeriods.length == 0;
    }
}
