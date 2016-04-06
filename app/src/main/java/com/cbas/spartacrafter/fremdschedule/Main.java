/**
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main extends AppCompatActivity {
    private static final String FREMD_URL = "http://fhs.d211.org/info/bell-schedule/";
    private static final String BELL_BANNER_CLASS = "bell-banner";
    private static String[] classNames;
    private static String[] scheduleNames;
    private static String[] classOrder = new String[11];
    private static long[][] scheduleStartTimes = new long[11][9];
    private static long[][] scheduleEndTimes = new long[11][9];
    private static DownloadWebpageTask d;
    private static int currentScheduleType = -1;
    private static Context context;
    private static SectionsPagerAdapter mSectionsPagerAdapter;
    private static ViewPager mViewPager;
    private static TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        try {
            readScheduleResources();
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Could not read resources: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            throw new RuntimeException("Could not read resources: " + e.getMessage());
        }
        //d = (DownloadWebpageTask) new DownloadWebpageTask().execute(FREMD_URL);
        //updateCurrentScheduleType();
        context = getApplicationContext();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), Schedule.TYPE_NORMAL, Schedule.TYPE_LATE_START, Schedule.TYPE_EARLY_DISMISSAL, Schedule.TYPE_PEP_ASSEMBLY);
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

    public void readScheduleResources() throws ParseException {
        classNames = getResources().getStringArray(R.array.default_class_names);
        scheduleNames = getResources().getStringArray(R.array.schedule_type_names);
        classOrder = getResources().getStringArray(R.array.schedule_class_order);
        String[] buffer = getResources().getStringArray(R.array.schedule_start_times);
        SimpleDateFormat format = new SimpleDateFormat("y-D-kk:mm", Locale.US);
        Calendar tem = Calendar.getInstance();
        for (int i = 0; i < buffer.length; i++) {
            String[] times = buffer[i].split(Pattern.quote("|"));
            for (int j = 0; j < times.length; j++) {
                scheduleStartTimes[i][j] = format.parse(tem.get(Calendar.YEAR) + "-" + tem.get(Calendar.DAY_OF_YEAR) + "-" + times[j]).getTime();
            }
        }
        buffer = getResources().getStringArray(R.array.schedule_end_times);
        for (int i = 0; i < buffer.length; i++) {
            String[] times = buffer[i].split(Pattern.quote("|"));
            for (int j = 0; j < times.length; j++) {
                scheduleEndTimes[i][j] = format.parse(tem.get(Calendar.YEAR) + "-" + tem.get(Calendar.DAY_OF_YEAR) + "-" + times[j]).getTime();
            }
        }
    }

    private void updateCurrentScheduleType() {
        //WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //wifiManager
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            new DownloadWebpageTask().execute(FREMD_URL);
            System.out.println("It has internet access");
        } else {

        }
    }

    private int getCurrentScheduleType() {
        return currentScheduleType;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        // The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    public static String getClassName(int i) {
        return classNames[i];
    }

    public static String getScheduleName(int scheduleType) {
        return scheduleNames[scheduleType];
    }

    public static int[] getClassOrder(int scheduleType) {
        String[] buffer = classOrder[scheduleType].split(Pattern.quote("|"));
        int[] ret = new int[buffer.length];
        for(int i = 0; i < ret.length; i++) {
            ret[i] = Integer.parseInt(buffer[i]);
        }
        return ret;
    }

    public static long[] getScheduleStartTimes(int scheduleType) {
        return scheduleStartTimes[scheduleType];
    }

    public static long[] getScheduleEndTimes(int scheduleType) {
        return scheduleEndTimes[scheduleType];
    }

    public static int getDP(int pixels) {
        return (int) (pixels * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    public static Context getContext() {
        return context;
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                Document document = Jsoup.connect(urls[0]).get();
                Element scheduleName = document.getElementsByClass("bell-banner").get(0);
                System.out.println(scheduleName.data());
                return scheduleName.data();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Could not connect to \"" + FREMD_URL +"\": " + e.getMessage(), Toast.LENGTH_SHORT).show();
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);
        }
    }
}
