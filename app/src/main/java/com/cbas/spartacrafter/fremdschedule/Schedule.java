/*
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

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
    private Date[] startTimes;
    private Date[] endTimes;
    private int[] classOrder;
    private String[] titles;

    public Schedule(int scheduleType) {
        this.scheduleType = scheduleType;
        startTimes = Main.getScheduleStartTimes(scheduleType);
        endTimes = Main.getScheduleEndTimes(scheduleType);
        classOrder = Main.getClassOrder(scheduleType);
        titles = Main.getClassNames();
    }

    public static Schedule getClassPeriodsFromFile(File classPeriodFile) throws IOException {
        /*
        InputStream inputFile;
        inputFile = new FileInputStream(classPeriodFile);
        String[] classNames = new String[16];
        byte[] classSettings = new byte[16];
        byte[] buffer = new byte[inputFile.available()];
        inputFile.read(buffer);
        TODO finish
        return null;
        */
        return new Schedule(SCHEDULE_TYPE_NORMAL);
    }

    public int getCurrentScheduleType() {
        //TODO finish
        return SCHEDULE_TYPE_NORMAL;
    }

    public boolean isPeriodActive(int periodIndex) {
        Date now = Calendar.getInstance().getTime();
        return now.after(startTimes[periodIndex]) && now.before(endTimes[periodIndex]);
    }

    public int getActivePeriod() {
        return activePeriod;
    }

    public Date getPeriodStartTimes(int periodIndex) {
        return startTimes[periodIndex];
    }

    public Date getPeriodEndTime(int periodIndex) {
        return endTimes[periodIndex];
    }

    public String getClassTitle(int periodIndex) {
        return titles[periodIndex];
    }

    public void setClassTitle(int periodIndex, String title) {
        titles[periodIndex] = title;
    }

    @Override
    public String toString() {
        String ret = "";
        for (String classTitle : titles) {
            ret += classTitle + '\n';
        }
        return ret;
    }
}
