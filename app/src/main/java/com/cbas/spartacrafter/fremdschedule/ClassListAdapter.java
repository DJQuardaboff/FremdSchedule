/**
 * Created by smith1246 on 2/17/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class ClassListAdapter extends RecyclerView.Adapter {
    private Schedule schedule;

    public ClassListAdapter(int scheduleType) {
        schedule = new Schedule(scheduleType);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return schedule.length();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        schedule.initClassPeriod(viewType, parent);
        return schedule.getClassPeriod(viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ClassPeriod) holder).setupView();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
