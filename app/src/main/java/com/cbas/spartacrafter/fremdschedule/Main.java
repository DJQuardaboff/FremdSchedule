package com.cbas.spartacrafter.fremdschedule;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.Date;

public class Main extends AppCompatActivity {
    private ViewPager mViewPager;
    private static Context context;
    private static String[] classNames;
    private static String[] scheduleNames;
    private static int[][] classOrder;
    private static Date[][] scheduleStartTimes;
    private static Date[][] scheduleEndTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readScheduleResources();
        setupListViews();
        context = getApplicationContext();
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.schedule_type_names));
        mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mSectionsPagerAdapter);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), true);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    public void readScheduleResources() {
        classNames = getResources().getStringArray(R.array.default_class_names);
        scheduleNames = getResources().getStringArray(R.array.schedule_type_names);
        String[] orders = getResources().getStringArray(R.array.schedule_class_order);
        for (int i = 0; i < orders.length) {

        }
    }

    public void setupListViews() {
        ListView list1 = (ListView) findViewById(R.id.schedule_1_list);
        list1.setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_NORMAL));
        ListView list2 = (ListView) findViewById(R.id.schedule_2_list);
        list2.setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_LATE_START));
        ListView list3 = (ListView) findViewById(R.id.schedule_3_list);
        list3.setAdapter(new ClassListAdapter(Schedule.SCHEDULE_TYPE_EARLY_DISMISSAL));
        ListView list4 = (ListView) findViewById(R.id.schedule_4_list);
        list4.setAdapter(new ClassListAdapter(getCurrentScheduleType()));
    }

    private int getCurrentScheduleType() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Context getContext() {
        return context;
    }

    public static String[] getClassNames() {
        return classNames;
    }

    public static String getScheduleName(int scheduleType) {
        return scheduleNames[scheduleType];
    }

    public static int[] getClassOrder(int scheduleType) {
        return classOrder[scheduleType];
    }

    public static Date[] getScheduleStartTimes(int scheduleType) {
        return scheduleStartTimes[scheduleType];
    }

    public static Date[] getScheduleEndTimes(int scheduleType) {
        return scheduleEndTimes[scheduleType];
    }
}
