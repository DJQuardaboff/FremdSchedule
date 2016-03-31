/**
 * Created by smith1246 on 2/26/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimerTask;

public class ClassPeriod extends RecyclerView.ViewHolder{
    private String title;
    private final String subtitle;
    private final int periodNum;
    private boolean isActive;
    private ProgressBar progressView;
    private final long startTime;
    private final long endTime;
    public final TimerTask updateTask = new TimerTask() {
        @Override
        public void run() {
            update();
        }
    };

    public ClassPeriod(int classNum, int periodNum, final long startTime, final long endTime, ViewGroup parent) {
        super(((LayoutInflater) Main.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.class_list_item, parent));
        this.periodNum = periodNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = Main.getClassName(classNum);
        subtitle = "Period " + (periodNum + 1);
    }

    public void setupView() {
        ((TextView) super.itemView.findViewById(R.id.itemTitle)).setText(title);
        ((TextView) super.itemView.findViewById(R.id.itemSubtitle)).setText(subtitle);
        ((TextView) super.itemView.findViewById(R.id.itemStartTime)).setText(Long.toString(startTime));
        ((TextView) super.itemView.findViewById(R.id.itemEndTime)).setText(Long.toString(endTime));
        progressView = (ProgressBar) super.itemView.findViewById(R.id.itemProgress);
        update();
    }

    public void update() {
        final long now = System.currentTimeMillis();
        isActive = now > startTime && now < endTime;
        if (progressView != null) {
            progressView.setProgress(getProgress());
        } else {
            progressView = (ProgressBar) super.itemView.findViewById(R.id.itemProgress);
        }
    }



    public int getProgress() {
        final long now = System.currentTimeMillis();
        return ((now < startTime) ? (0) : ((now < endTime) ? ((int) (100 * (now - startTime) / (endTime - startTime))) : (100)));
    }

    public int getPeriodNum() {
        return periodNum;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
