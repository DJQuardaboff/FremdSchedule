/*
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ListView;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private String[] sectionNames;

    public SectionsPagerAdapter(FragmentManager fm, String[] sectionNames) {
        super(fm);
        this.sectionNames = sectionNames;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return sectionNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sectionNames[position];
    }
}