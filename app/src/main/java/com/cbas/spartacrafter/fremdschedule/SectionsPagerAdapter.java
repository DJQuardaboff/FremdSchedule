/**
 * Created by smith1246 on 2/12/2016.
 */

package com.cbas.spartacrafter.fremdschedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private PageFragment[] pages;

    public SectionsPagerAdapter(FragmentManager fm, int[] sectionTypes) {
        super(fm);
        pages = new PageFragment[sectionTypes.length];
        createPages(sectionTypes);
    }

    public void createPages(int[] titles) {
        for(int i = 0; i < pages.length; i++) {
            createPage(i, titles[i]);
        }
    }

    public void createPage(int pos, int scheduleType) {
        pages[pos] = PageFragment.newInstance(scheduleType);
    }

    public void destroyPages() {
        for(int i = 0; i < pages.length; i++) {
            destroyPage(i);
        }
    }

    public void destroyPage(int pos) {
        pages[pos] = null;
    }

    @Override
    public Fragment getItem(int pos) {
        return pages[pos];
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @Override
    public CharSequence getPageTitle(int pos) {
        return Main.getScheduleName(pages[pos].getScheduleType());
    }
}