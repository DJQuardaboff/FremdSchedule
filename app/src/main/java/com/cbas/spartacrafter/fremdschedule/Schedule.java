/**
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

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
    private static Timer timer = new Timer();
    private ArrayList<ClassPeriod> classes = new ArrayList<>();
    private int scheduleType;

    public Schedule(int scheduleType) {
        this.scheduleType = scheduleType;
        int[] order = Main.getClassOrder(scheduleType);
        long[] startTimes = Main.getScheduleStartTimes(scheduleType);
        long[] endTimes = Main.getScheduleEndTimes(scheduleType);
        for (int i = 0; (i < order.length) && (order[i] > -1); i++) {
            classes.add(new ClassPeriod(order[i], i, startTimes[i], endTimes[i]));
            //scheduleUpdate(i);
        }
    }public void scheduleUpdates() {
        for(int i = 0; i < classes.size(); i++) {
            scheduleUpdate(i);
        }
    }

    public void scheduleUpdate(int periodNum) {
        timer.schedule(classes.get(periodNum).updateTask, 0, 60000);
    }

    public int getSchduleType() {
        return scheduleType;
    }

    public int size() {
        return classes.size();
    }

    public ClassPeriod getClassPeriod(int periodIndex) {
        return classes.get(periodIndex);
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

    public static Timer getTimer() {
        return timer;
    }
}
