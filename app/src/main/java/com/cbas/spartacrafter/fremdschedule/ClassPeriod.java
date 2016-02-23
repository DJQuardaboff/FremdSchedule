/*
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.support.v4.util.Pair;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

public class ClassPeriod{
    public static final String SCHEDULE_TYPE_NORMAL = "Normal Schedule";
    public static final String SCHEDULE_TYPE_LATE_START = "Late Start";
    public static final String SCHEDULE_TYPE_HALF = 2;
    public static final int HALF_TYPE_FIRST = 0;
    public static final int HALF_TYPE_SECOND = 2;
    public static final int HALF_TYPE_BOTH = 1;
    private static final int[][][][] PERIOD_START_END_TIMES = {
            {
                    {{7, 30}, {8, 20}},
                    {{8, 25}, {9, 15}},
                    {{9, 20}, {10, 10}},
                    {{10, 15}, {11, 5}},
                    {{11, 10}, {12, 0}},
                    {{12, 5}, {12, 55}},
                    {{13, 0}, {13, 50}},
                    {{13, 55}, {14, 45}},
            }, // Normal Day 7:30-14:45
            {
                    {{8, 50}, {9, 30}},
                    {{9, 35}, {10, 15}},
                    {{10, 20}, {11, 0}},
                    {{11, 5}, {11, 45}},
                    {{11, 50}, {12, 30}},
                    {{12, 35}, {13, 15}},
                    {{13, 20}, {14, 0}},
                    {{14, 5}, {14, 45}},
            }, // Late Start Day 8:50-14:45
            {
                    {{7, 30}, {7, 58}},
                    {{8, 3}, {8, 32}},
                    {{8, 37}, {9, 5}},
                    {{9, 10}, {9, 38}},
                    {{9, 43}, {10, 11}},
                    {{10, 16}, {10, 44}},
                    {{10, 49}, {11, 17}},
                    {{11, 22}, {11, 50}},
            }, // Half Day 7:30-11:50
    };
    private final Date startTime;
    private final Date endTime;
    private String title;
    private int periodNum;
    private int periodType;

    public ClassPeriod(String title, int periodNum, int periodType) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, PERIOD_START_END_TIMES[periodType][periodNum][0][0]);
        c.set(Calendar.MINUTE, PERIOD_START_END_TIMES[periodType][periodNum][0][1]);
        startTime = c.getTime();
        c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, PERIOD_START_END_TIMES[periodType][periodNum][1][0]);
        c.set(Calendar.MINUTE, PERIOD_START_END_TIMES[periodType][periodNum][1][1]);
        endTime = c.getTime();
        this.title = title;
        this.periodNum = periodNum;
        this.periodType = periodType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public static ClassPeriod[] getClassPeriodsFromFile(File classPeriodFile) throws IOException {
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
        return new ClassPeriod[] {
                new ClassPeriod("Period 1", 1, HALF_TYPE_BOTH),
                new ClassPeriod("Period 2", 2, HALF_TYPE_BOTH),
                new ClassPeriod("Period 3", 3, HALF_TYPE_BOTH),
                new ClassPeriod("Period 4", 4, HALF_TYPE_BOTH),
                new ClassPeriod("Period 5", 5, HALF_TYPE_BOTH),
                new ClassPeriod("Period 6", 6, HALF_TYPE_BOTH),
                new ClassPeriod("Period 7", 7, HALF_TYPE_BOTH),
                new ClassPeriod("Period 8", 8, HALF_TYPE_BOTH),
        };
    }

    public void addOnStartListener(){

    }

    public boolean isInSession() {
        Date now = Calendar.getInstance().getTime();
        return now.after(startTime) && now.before(endTime);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClassNum() {
        return periodNum;
    }

    public void setClassNum(int classNum) {
        this.periodNum = classNum;
    }

    @Override
    public String toString () {
        return title;
    }
}
