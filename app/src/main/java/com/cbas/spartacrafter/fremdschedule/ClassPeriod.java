/**
 * Created by smith1246 on 2/26/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ClassPeriod {
    private String title;
    private String subtitle;
    private boolean isActive;
    private ProgressBar progressView;
    private final int periodNum;
    private final long startTime;
    private final long endTime;
    public final TimerTask updateTask = new TimerTask() {
        @Override
        public void run() {
            update();
        }
    };

    public ClassPeriod(int classNum, int periodNum, final long startTime, final long endTime) {
        this.periodNum = periodNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = Main.getClassName(classNum);
        subtitle = "Period " + periodNum;
    }

    public void update() {
        final long now = Calendar.getInstance().getTime().getTime();
        isActive = now > startTime && now < endTime;
        if(isActive() && progressView != null) {
            progressView.setProgress((int) (100 * (now - startTime) / (endTime - startTime)));
        }
    }

    public View getView(LayoutInflater inflater, ViewGroup parent) {
        RelativeLayout itemLayout;
        itemLayout = (RelativeLayout) inflater.inflate(R.layout.class_list_item, parent, false);
        ((TextView) itemLayout.findViewById(R.id.itemTitle)).setText(title);
        ((TextView) itemLayout.findViewById(R.id.itemSubtitle)).setText(subtitle);
        progressView = (ProgressBar) itemLayout.findViewById(R.id.itemProgress);
        update();
        return itemLayout;
    }

    public int getProgress() {
        return progressView.getProgress();
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
