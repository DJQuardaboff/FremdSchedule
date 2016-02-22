/*
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.support.v4.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

public class ClassPeriod {
    public static final int DAY_TYPE_NORMAL = 0;
    public static final int DAY_TYPE_LATE_START = 1;
    public static final int DAY_TYPE_HALF = 2;
    public static final int PERIOD_TYPE_FIRST_HALF = 0;
    public static final int PERIOD_TYPE_SECOND_HALF = 2;
    public static final int PERIOD_TYPE_ALL = 1;
    private final Date startTime;
    private final Date endTime;
    private String title;
    private int periodNum;
    private static final int[][][] NORMAL_DAY_PERIOD_TIMES = {
            {{7,30}, {8,20}},
            {{8,25}, {9,15}},
            {{9,20}, {10,10}},
            {{10,15}, {11,05}},
            {{11,10}, {12,00}},
            {{12,05}, {12,55}},
            {{13,00}, {13,50}},
            {{13,55}, {14,45}},
    }; // 7:25-14:45
    private static final int[][][] LATE_START_DAY_PERIOD_TIMES = {
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
    }; // 8:45-14:45
    private static final int[][][] LATE_START_ASSEMBLY_DAY_PERIOD_TIMES = {
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
            {{},{},},
    }; // 8:45-14:40
    private static final long[] HALF_DAY_PERIOD_TIMES = {

    }; // 7:25-11:50

    public ClassPeriod(String title, int periodNum, int periodType) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.);
        c.set(Calendar.MINUTE, );
        c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, );
        c.set(Calendar.MINUTE, );
        this.title = title;
        this.periodNum = periodNum;
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
                new ClassPeriod("Period 1", 1, PERIOD_TYPE_ALL),
                new ClassPeriod("Period 2", 2, PERIOD_TYPE_ALL),
                new ClassPeriod("Period 3", 3, PERIOD_TYPE_ALL),
                new ClassPeriod("Period 4", 4, PERIOD_TYPE_ALL),
                new ClassPeriod("Period 5", 5, PERIOD_TYPE_ALL),
                new ClassPeriod("Period 6", 6, PERIOD_TYPE_ALL),
                new ClassPeriod("Period 7", 7, PERIOD_TYPE_ALL),
                new ClassPeriod("Period 8", 8, PERIOD_TYPE_ALL),
        };
    }

    public void addOnStartListener(){

    }

    public boolean isInSession(ClassPeriod c, int dayType) {
        Date d = Calendar.getInstance().getTime();
        return d.after(new Date());
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
