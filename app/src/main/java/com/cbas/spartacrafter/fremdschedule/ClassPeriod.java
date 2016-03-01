package com.cbas.spartacrafter.fremdschedule;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by smith1246 on 2/26/2016.
 */
public class ClassPeriod extends TimerTask{
    private String title;
    private final Date startTime;
    private final Date endTime;
    private boolean isActive;

    public ClassPeriod(String title, Date startTime, Date endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        final Date now = Calendar.getInstance().getTime();
        isActive = now.after(startTime) && now.before(endTime);
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

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void run() {
        final Date now = Calendar.getInstance().getTime();
        isActive = now.after(startTime) && now.before(endTime);
    }
}
