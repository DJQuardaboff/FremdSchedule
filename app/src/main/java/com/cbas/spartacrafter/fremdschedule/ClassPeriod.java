/**
 * Created by smith1246 on 2/26/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.view.View;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ClassPeriod{
    private String title;
    private long startTime;
    private long endTime;
    private boolean isActive;

    public ClassPeriod(String title, final long startTime, final long endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        final long now = Calendar.getInstance(Locale.US).getTime().getTime();
        Timer t = new Timer("timer");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final long now = Calendar.getInstance().getTime().getTime();
                isActive = now > startTime && now < endTime;
            }
        };
        t.scheduleAtFixedRate(task, startTime - now, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        t.scheduleAtFixedRate(task, endTime - now, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        isActive = now > this.startTime && now < this.endTime;
    }

    public View getView() {
        //TODO finish
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
