/**
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class Main extends AppCompatActivity {
    private static Context context;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static float displayScale;
    private static String[] classNames;
    private static String[] scheduleNames;
    private static int[][] classOrder = new int[11][9];
    private static long[][] scheduleStartTimes = new long[11][9];
    private static long[][] scheduleEndTimes = new long[11][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate()");
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        displayScale = context.getResources().getDisplayMetrics().density;
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        try {
            readScheduleResources();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read resources: " + e.getMessage());
        }
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), new String[]{scheduleNames[0], scheduleNames[2], scheduleNames[3], scheduleNames[10]});
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setTabsFromPagerAdapter(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart()");
        context = getApplicationContext();
        //TODO finish
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume()");
        context = getApplicationContext();
        //TODO finish
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("onPause()");
        context = null;
        //TODO finish
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("onStop()");
        //TODO finish
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy()");
        //TODO finish
    }

    public void readScheduleResources() throws ParseException {
        classNames = getResources().getStringArray(R.array.default_class_names);
        scheduleNames = getResources().getStringArray(R.array.schedule_type_names);
        String[] buffer = getResources().getStringArray(R.array.schedule_class_order);
        for (int i = 0; i < buffer.length; i++) {
            String[] order = buffer[i].split(Pattern.quote("|"));
            for (int j = 0; j < order.length; j++) {
                classOrder[i][j] = Integer.parseInt(order[j]);
            }
        }
        buffer = getResources().getStringArray(R.array.schedule_start_times);
        SimpleDateFormat format = new SimpleDateFormat("kk:mm", Locale.getDefault());
        for (int i = 0; i < buffer.length; i++) {
            String[] times = buffer[i].split(Pattern.quote("|"));
            for (int j = 0; j < times.length; j++) {
                scheduleStartTimes[i][j] = format.parse(times[j]).getTime();
            }
        }
        buffer = getResources().getStringArray(R.array.schedule_end_times);
        for (int i = 0; i < buffer.length; i++) {
            String[] times = buffer[i].split(Pattern.quote("|"));
            for (int j = 0; j < times.length; j++) {
                scheduleEndTimes[i][j] = format.parse(times[j]).getTime();
            }
        }
    }

    private int getCurrentScheduleType() {
        return Schedule.SCHEDULE_TYPE_NORMAL;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    public static Context getContext() {
        return context;
    }

    public static String getClassName(int i) {
        return classNames[i];
    }

    public static String getScheduleName(int scheduleType) {
        return scheduleNames[scheduleType];
    }

    public static int[] getClassOrder(int scheduleType) {
        return classOrder[scheduleType];
    }

    public static long[] getScheduleStartTimes(int scheduleType) {
        return scheduleStartTimes[scheduleType];
    }

    public static long[] getScheduleEndTimes(int scheduleType) {
        return scheduleEndTimes[scheduleType];
    }

    public static int getDP(int pixles) {
        return (int) (pixles * displayScale + 0.5f);
    }
}
