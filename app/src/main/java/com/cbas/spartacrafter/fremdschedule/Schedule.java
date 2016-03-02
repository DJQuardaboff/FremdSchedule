/*
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import java.util.Date;
import java.util.Timer;

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
    private int scheduleType;
    private Timer timer = new Timer();
    private ClassPeriod[] classes;

    public Schedule(int scheduleType) {
        this.scheduleType = scheduleType;
        String[] titles = Main.getClassNames();
        int[] order = Main.getClassOrder(scheduleType);
        Date[] startTimes = Main.getScheduleStartTimes(scheduleType);
        Date[] endTimes = Main.getScheduleEndTimes(scheduleType);
        classes = new ClassPeriod[order.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = new ClassPeriod(titles[order[i]], startTimes[i], endTimes[i]);
            ClassPeriod c = classes[i];
            timer.schedule(c, new Date(c.getStartTime().getTime() + 1000));
            timer.schedule(c, new Date(c.getEndTime().getTime() + 1000));
        }
    }

    public int getSchduleType() {
        return scheduleType;
    }

    public int length() {
        return classes.length;
    }

    public ClassPeriod getClassPeriod(int periodIndex) {
        return classes[periodIndex];
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
}
