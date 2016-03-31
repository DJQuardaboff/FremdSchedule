/**
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Schedule {
    public static final int SCHEDULE_TYPE_NORMAL = 0;
    public static final int SCHEDULE_TYPE_SUMMER_SCHOOL = 1;
    public static final int SCHEDULE_TYPE_LATE_START = 2;
    public static final int SCHEDULE_TYPE_EARLY_DISMISSAL = 3;
    public static final int SCHEDULE_TYPE_FINALS_DAY_1 = 4;
    public static final int SCHEDULE_TYPE_FINALS_DAY_2 = 5;
    public static final int SCHEDULE_TYPE_FINALS_DAY_3 = 6;
    public static final int SCHEDULE_TYPE_PSAE_ACT = 7;
    public static final int SCHEDULE_TYPE_PSAE_PLAN = 8;
    public static final int SCHEDULE_TYPE_AWARDS_ASSEMBLY = 9;
    public static final int SCHEDULE_TYPE_PEP_ASSEMBLY = 10;
    private final ClassPeriod[] classes;
    private final int scheduleType;
    private final int[] order;
    private final long[] startTimes;
    private final long[] endTimes;
    //private static Timer timer = new Timer();

    public Schedule(int scheduleType) {
        this.scheduleType = scheduleType;
        order = Main.getClassOrder(scheduleType);
        startTimes = Main.getScheduleStartTimes(scheduleType);
        endTimes = Main.getScheduleEndTimes(scheduleType);
        classes = new ClassPeriod[order.length];
        for(int i = 0; i < classes.length; i++) {
            classes[i] = new ClassPeriod(order[i], i, startTimes[i], endTimes[i], null);
        }
    }

    public int getSchduleType() {
        return scheduleType;
    }

    public int length() {
        return classes.length;
    }

    public ClassPeriod getClassPeriod(int periodNum) {
        return classes[periodNum];
    }

    public ClassPeriod getActivePeriod() {
        ClassPeriod activeClass = null;
        for (ClassPeriod c:classes) {
            if (c.isActive()) {
                activeClass = c;
            }
        }
        return activeClass;
    }

    /*public void scheduleUpdates() {
        for(int i = 0; i < classes.length; i++) {
            scheduleUpdate(i);
        }
    }

    public void scheduleUpdate(int periodNum) {
        timer.schedule(classes[periodNum].updateTask, 0, 60000);
    }

    public static Timer getTimer() {
        return timer;
    }*/
}
